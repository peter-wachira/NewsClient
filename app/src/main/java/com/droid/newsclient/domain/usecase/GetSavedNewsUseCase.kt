package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.articles.Article
import com.droid.newsclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase (private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}
