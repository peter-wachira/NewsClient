package com.droid.newsclient.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.droid.newsapiclient.R
import com.droid.newsapiclient.databinding.ActivityMainBinding
import com.droid.newsclient.presentation.adapter.NewsAdapter
import com.droid.newsclient.presentation.adapter.SourcesAdapter
import com.droid.newsclient.presentation.viewmodel.NewsViewModel
import com.droid.newsclient.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var sourcesAdapter: SourcesAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNaviagtion.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, factory)
                .get(NewsViewModel::class.java)

    }

}