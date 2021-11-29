package com.droid.newsclient.presentation.di

import android.app.Application
import com.droid.newsclient.domain.usecase.GetNewsHeadlinesUseCase
import com.droid.newsclient.domain.usecase.GetSearchNewsUseCase
import com.droid.newsclient.domain.usecase.GetTechArticlesUseCase
import com.droid.newsclient.domain.usecase.GetTechSourcesUseCase
import com.droid.newsclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(application: Application,
                                    getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase, getSearchNewsUseCase: GetSearchNewsUseCase,getTechArticlesUseCase: GetTechArticlesUseCase,getTechSourcesUseCase: GetTechSourcesUseCase): NewsViewModelFactory {
        return NewsViewModelFactory(application, getNewsHeadlinesUseCase,getSearchNewsUseCase,getTechArticlesUseCase,getTechSourcesUseCase)

    }
}