package com.droid.newsclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.NewsFragmentLayoutBinding
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.data.util.extensions.showErrorSnackbar
import com.droid.newsclient.presentation.adapter.NewsAdapter
import com.droid.newsclient.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class NewsFragment : Fragment() {
    private var page = 1
    private var country = "us"
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter


    private val fragmentNewsBinding: NewsFragmentLayoutBinding by lazy{
        NewsFragmentLayoutBinding.inflate(layoutInflater)
    }
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = fragmentNewsBinding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        viewArticleDetails()
        initRecyclerView()
        viewNewsList()
    }

    private fun viewArticleDetails() {
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            //pass bundle to info fragment
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
        }

        fragmentNewsBinding.materialTextView2.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_techSourcesFragment)
        }
    }


    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }

    }


    private fun viewNewsList() {
        viewModel.getAllArticles(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, { response ->
            when (response) {

                is Resource.Success -> {
                    Timber.e("response:  ${response.data}")

                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        when {
                            it.totalResults % 20 == 0 -> {
                                pages = it.totalResults / 20
                            }
                            else -> {
                                pages = it.totalResults / 20 + 1
                            }
                        }
                        isLastPage = page == pages
                    }
                }
                is Error -> {
                    hideProgressBar()
                    response.message?.let {

                        fragmentNewsBinding.root.showErrorSnackbar(
                                "An error occurred : $it",
                                Snackbar.LENGTH_LONG
                        )

                    }

                }


                is Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

    private fun showProgressBar() {
        isLoading = true
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }


    /*
        todo refactor boilerplate code
        todo remove Manual Pager Adapter & replace with Pager library
    */
    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getAllArticles(country, page)
                isScrolling = false

            }


        }
    }

}


