package com.droid.newsclient.presentation.ui


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.droid.newsclient.R
import com.droid.newsclient.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment() {


    private lateinit var fragmentSearchNewsBinding: FragmentSearchNewsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSearchNewsBinding = FragmentSearchNewsBinding.bind(view)
        requireActivity().window.statusBarColor = Color.WHITE

    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }


}