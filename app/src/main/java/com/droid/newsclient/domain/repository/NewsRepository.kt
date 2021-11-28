package com.droid.newsclient.domain.repository

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.articles.Article
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import com.droid.newsclient.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {


    //Get all articles from API
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(country: String,searchQuery:String, page: Int): Resource<APIResponse>

    // View all sources with a technology category
    suspend fun getAllTechSourcesCategory(category: String, source: String, page: Int):Resource<SourcesApiResponse>

    //View Only Technology related Articles from a given source
    suspend fun getTechRelatedArticles(category:String,source: String,page:Int): Resource<APIResponse>

    // Get items locally
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>







}