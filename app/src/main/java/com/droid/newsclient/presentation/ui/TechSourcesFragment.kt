package com.droid.newsclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.FragmentTechSourcesBinding
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.data.util.extensions.showErrorSnackbar
import com.droid.newsclient.presentation.adapter.SourcesAdapter
import com.droid.newsclient.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class TechSourcesFragment : Fragment() {
    private var source = ""
    private var page = 1
    private var category = "technology"
    private lateinit var viewModel: NewsViewModel
    private lateinit var sourcesAdapter: SourcesAdapter
    private val fragmentNewsBinding: FragmentTechSourcesBinding by lazy {
        FragmentTechSourcesBinding.inflate(layoutInflater)
    }
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = fragmentNewsBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        sourcesAdapter = (activity as MainActivity).sourcesAdapter

        initRecyclerView()
        viewNewsList()
        viewTechArticlesFromSource()

    }

    private fun viewTechArticlesFromSource() {
        sourcesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_source", it)
            }
            //pass bundle to news fragment
            findNavController().navigate(R.id.action_techSourcesFragment_to_newsFragment, bundle)
        }
    }


    private fun initRecyclerView() {
        sourcesAdapter = SourcesAdapter()
        fragmentNewsBinding.rvSources.apply {
            adapter = sourcesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun viewNewsList() {
        viewModel.getSourcesWithTech(category, source, page)
        viewModel.sourcesWithTech.observe(viewLifecycleOwner, { response ->
            when (response) {

                is Resource.Success -> {
                    Timber.e("response:  ${response.data}")

                    hideProgressBar()
                    response.data?.let {
                        sourcesAdapter.differ.submitList(it.sources.toList())
                        when {
                            it.sources.size % 20 == 0 -> {
                                pages = it.sources.size / 20
                            }
                            else -> {
                                pages = it.sources.size / 20 + 1
                            }
                        }
                        isLastPage = page == pages
                    }
                }


                else -> {
                    hideProgressBar()
                    response.message?.let {

                        fragmentNewsBinding.root.showErrorSnackbar(
                                "An error occurred : $it",
                                Snackbar.LENGTH_LONG
                        )

                    }

                }
            }
        })
    }


    private fun hideProgressBar() {
        isLoading = false
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }


}