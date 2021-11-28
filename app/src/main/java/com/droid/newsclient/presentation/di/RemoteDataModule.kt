package com.droid.newsclient.presentation.di

import com.droid.newsclient.data.api.NewsAPIService
import com.droid.newsclient.data.repository.datasource.NewsRemoteDataSource
import com.droid.newsclient.data.repository.datasourceImpl.NewsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
            newsAPIService: NewsAPIService
    ): NewsRemoteDataSource {
        return NewsDataSourceImpl(newsAPIService)
    }


}