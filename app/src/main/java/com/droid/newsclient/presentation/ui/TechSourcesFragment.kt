package com.droid.newsclient.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.FragmentTechSourcesBinding
import com.droid.newsapiclient.databinding.NewsFragmentLayoutBinding
import com.droid.newsclient.presentation.adapter.NewsAdapter
import com.droid.newsclient.presentation.viewmodel.NewsViewModel


class TechSourcesFragment : Fragment() {
    private var source = ""
    private var page = 1
    private var category = "technology"
    private var country = "us"
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private val fragmentNewsBinding: FragmentTechSourcesBinding by lazy {
        FragmentTechSourcesBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = fragmentNewsBinding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    private fun observeViewModel() {

    }



    private fun initRecyclerView() {
        // newsAdapter = NewsAdapter()
        fragmentNewsBinding.rvSources.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        fragmentNewsBinding.progressBar.visibility = View.INVISIBLE
    }



}