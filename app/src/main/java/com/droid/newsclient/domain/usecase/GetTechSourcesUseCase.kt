package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.sources.SourcesApiResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository

class GetTechSourcesUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(apiKey:String,category:String,page:Int): Resource<SourcesApiResponse> {
        return newsRepository.getAllTechSourcesCategory(apiKey,category,page)
    }
}