package com.droid.newsclient.presentation.di

import com.droid.newsclient.data.repository.NewsRepositoryImpl
import com.droid.newsclient.data.repository.datasource.NewsRemoteDataSource
import com.droid.newsclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
            newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}