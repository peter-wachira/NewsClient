package com.droid.newsclient.data.api

import com.droid.newsapiclient.BuildConfig
import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
            @Query("country")
            country: String,
            @Query("page")
            page: Int,
            @Query("apiKey")
            apiKey: String = BuildConfig.API_KEY): Response<APIResponse>


    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
            @Query("country")
            country: String,
            @Query("q")
            searchQuery: String,
            @Query("page")
            page: Int,
            @Query("apiKey")
            apiKey: String = BuildConfig.API_KEY): Response<APIResponse>

    @GET("v2/top-headlines/sources")
    suspend fun getTechCategorySources(
            @Query("category")
            category: String,
            @Query("source")
            source: String,
            @Query("page")
            page: Int,
            @Query("apiKey")
            apiKey: String = BuildConfig.API_KEY): Response<SourcesApiResponse>

    @GET("v2/top-headlines")
    suspend fun getTechArticlesFromSource(
            @Query("category")
            category: String,
            @Query("source")
            source: String,
            @Query("page")
            page: Int,
            @Query("apiKey")
            apiKey: String = BuildConfig.API_KEY): Response<APIResponse>

}