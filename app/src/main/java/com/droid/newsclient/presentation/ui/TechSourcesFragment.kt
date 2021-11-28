package com.droid.newsclient.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.FragmentTechSourcesBinding
import com.droid.newsapiclient.databinding.NewsFragmentLayoutBinding


class TechSourcesFragment : Fragment() {
    private lateinit var techSourcesBinding: FragmentTechSourcesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tech_sources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        techSourcesBinding = FragmentTechSourcesBinding.bind(view)

    }


}