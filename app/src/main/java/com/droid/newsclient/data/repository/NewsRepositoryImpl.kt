package com.droid.newsclient.data.repository

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.articles.Article
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import com.droid.newsclient.data.repository.datasource.NewsRemoteDataSource
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
        private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(country: String, searchQuery: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedTopHeadlines(country, searchQuery, page))
    }

    override suspend fun getAllTechSourcesCategory(category: String, source: String, page: Int): Resource<SourcesApiResponse> {
        return sourceResponseToResource(newsRemoteDataSource.getTechCategorySources(category, source, page))
    }

    override suspend fun getTechRelatedArticles(category: String, source: String, page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedTopHeadlines(category, source, page))
    }
    

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }


    //function to input the response instance of type API Response & returned from the API and output a resource instance of type APIResponse<> that contains different load states i.e success loading & error
    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    private fun sourceResponseToResource(response: Response<SourcesApiResponse>): Resource<SourcesApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}