package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository


class GetSearchNewsUseCase (private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page:Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(country,searchQuery,page)
    }

}