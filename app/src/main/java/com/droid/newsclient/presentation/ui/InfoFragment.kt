package com.droid.newsclient.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.droid.newsapiclient.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    private val binding: FragmentInfoBinding by lazy {
        FragmentInfoBinding.inflate(layoutInflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInfoFragmentArgs()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) = binding.root


    private fun getInfoFragmentArgs(){
        val args: InfoFragmentArgs by navArgs()
        val article= args.selectedArticle
        binding.webViewInfo.apply {
            webViewClient = WebViewClient()
            if (article.url != null){
                loadUrl(article.url)
            }
        }
    }

}