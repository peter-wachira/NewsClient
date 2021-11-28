package com.droid.newsclient.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.model.sources.SourcesApiResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.usecase.GetNewsHeadlinesUseCase
import com.droid.newsclient.domain.usecase.GetSearchNewsUseCase
import com.droid.newsclient.domain.usecase.GetTechArticlesUseCase
import com.droid.newsclient.domain.usecase.GetTechSourcesUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NewsViewModel(
        private val app: Application,
        private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
        private val getSearchNewsUseCase: GetSearchNewsUseCase,
        private val getTechArticlesUseCase: GetTechArticlesUseCase,
        private val getTechSourcesUseCase: GetTechSourcesUseCase
) : AndroidViewModel(app) {

    private val newsHeadLines: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    //View all articles
    fun getAllArticles(country: String, page: Int) = viewModelScope.launch(IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {

                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }
    }

    //Technology related articles from a given source
    private val techArticlesFromSource: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun getTechArticlesFromSource(category: String, source: String, page: Int) = viewModelScope.launch{
        techArticlesFromSource.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getTechArticlesUseCase.execute(category, source, page)
                techArticlesFromSource.postValue(response)
            } else {
                techArticlesFromSource.postValue(Resource.Error("No Internet Connection"))
            }
        } catch (e: Exception) {
            techArticlesFromSource.postValue(Resource.Error(e.message.toString()))
        }
    }


    //View all sources with a technology category
    private val sourcesWithTech: MutableLiveData<Resource<SourcesApiResponse>> = MutableLiveData()
    fun getSourcesWithTech(category: String, source: String, page: Int)= viewModelScope.launch{
        sourcesWithTech.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val response = getTechSourcesUseCase.execute(category, source, page)
                sourcesWithTech.postValue(response)
            }else{
                sourcesWithTech.postValue(Resource.Error("No internet"))
            }
        }catch (e:Exception){
            sourcesWithTech.postValue(Resource.Error(e.message.toString()))
        }
    }

    //Search news implementation
    private val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()
    fun searchNews(
            country: String,
            searchQuery: String,
            page: Int
    ) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchNewsUseCase.execute(country, searchQuery, page)
                searchedNews.postValue(response)
            } else {
                searchedNews.postValue(Resource.Error("No internet connection"))
            }
        } catch (e: Exception) {
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }
    }


    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

}