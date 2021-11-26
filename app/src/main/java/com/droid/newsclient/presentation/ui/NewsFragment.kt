package com.droid.newsclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.NewsFragmentLayoutBinding


class NewsFragment : Fragment() {

    private lateinit var fragmentNewsBinding: NewsFragmentLayoutBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.news_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNewsBinding = NewsFragmentLayoutBinding.bind(view)

    }


}


