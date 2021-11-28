package com.droid.newsclient.data.repository.datasourceImpl

import com.droid.newsclient.data.api.NewsAPIService
import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import com.droid.newsclient.data.repository.datasource.NewsRemoteDataSource
import com.droid.newsclient.data.util.Resource
import retrofit2.Response

class NewsDataSourceImpl(
        private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedTopHeadlines(country: String, searchQuery: String, page: Int): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlines(country, searchQuery, page)
    }

    override suspend fun getTechCategorySources(category: String, source: String, page: Int): Response<SourcesApiResponse> {
        return newsAPIService.getTechCategorySources(category, source, page)
    }

    override suspend fun getTechArticlesFromSource(category: String, source: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTechArticlesFromSource(category, source, page)
    }



}