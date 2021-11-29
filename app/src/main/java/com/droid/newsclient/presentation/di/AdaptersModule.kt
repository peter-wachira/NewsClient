package com.droid.newsclient.presentation.di

import com.droid.newsclient.presentation.adapter.NewsAdapter
import com.droid.newsclient.presentation.adapter.SourcesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AdaptersModule {
    @Singleton
    @Provides
    fun provideNewsAdapterModule(): NewsAdapter{
        return   NewsAdapter()
    }

    @Singleton
    @Provides
    fun provideSourcesAdapterModule():SourcesAdapter{
        return SourcesAdapter()
    }
}