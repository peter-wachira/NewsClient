package com.droid.newsclient.presentation.di

import com.droid.newsclient.domain.repository.NewsRepository
import com.droid.newsclient.domain.usecase.GetNewsHeadlinesUseCase
import com.droid.newsclient.domain.usecase.GetSearchNewsUseCase
import com.droid.newsclient.domain.usecase.GetTechArticlesUseCase
import com.droid.newsclient.domain.usecase.GetTechSourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNewsHeadlinesUseCaseModule(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsHeadlinesUseCaseModule(newsRepository: NewsRepository): GetSearchNewsUseCase {
        return GetSearchNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetTechArticlesUseCaseModule(newsRepository: NewsRepository): GetTechArticlesUseCase {
        return GetTechArticlesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetTechSourcesUseCaseModule(newsRepository: NewsRepository): GetTechSourcesUseCase {
        return GetTechSourcesUseCase(newsRepository)
    }

}

