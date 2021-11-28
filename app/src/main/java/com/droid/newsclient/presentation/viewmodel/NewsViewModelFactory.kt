package com.droid.newsclient.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droid.newsclient.domain.usecase.GetNewsHeadlinesUseCase
import com.droid.newsclient.domain.usecase.GetSearchNewsUseCase
import com.droid.newsclient.domain.usecase.GetTechArticlesUseCase
import com.droid.newsclient.domain.usecase.GetTechSourcesUseCase

class NewsViewModelFactory(
        private val  app:Application,
        private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        private val getSearchNewsUseCase: GetSearchNewsUseCase,
        private val getTechArticlesUseCase: GetTechArticlesUseCase,
        private val getTechSourcesUseCase: GetTechSourcesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
                app,
                getNewsHeadlinesUseCase,
                getSearchNewsUseCase,
                getTechArticlesUseCase,
                getTechSourcesUseCase
        ) as T
    }
}