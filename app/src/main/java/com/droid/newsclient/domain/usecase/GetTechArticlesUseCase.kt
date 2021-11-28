package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository

class GetTechArticlesUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(category:String,source: String,page:Int): Resource<APIResponse> {
        return newsRepository.getTechRelatedArticles(category,source,page)
    }

}