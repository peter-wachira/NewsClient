package com.droid.newsclient.data.repository.datasource

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse>
    suspend fun getSearchedTopHeadlines(country: String, searchQuery: String, page: Int): Response<APIResponse>
    suspend fun getTechCategorySources(category: String, source: String, page: Int): Response<SourcesApiResponse>
    suspend fun getTechArticlesFromSource(category: String, source: String, page: Int): Response<APIResponse>
}