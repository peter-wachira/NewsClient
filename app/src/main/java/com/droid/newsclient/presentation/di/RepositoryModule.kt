package com.droid.newsclient.presentation.di

import com.droid.newsclient.data.repository.NewsRepositoryImpl
import com.droid.newsclient.data.repository.datasource.NewsRemoteDataSource
import com.droid.newsclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
            newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return  NewsRepositoryImpl(newsRemoteDataSource)
    }
}