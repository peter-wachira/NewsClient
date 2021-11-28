package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository


class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country : String, page : Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country,page)
    }


}