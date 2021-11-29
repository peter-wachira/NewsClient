package com.droid.newsclient.domain.usecase

import com.droid.newsclient.data.model.articles.APIResponse
import com.droid.newsclient.data.util.Resource
import com.droid.newsclient.domain.repository.NewsRepository

class GetTechArticlesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(category: String, source: String, page: Int): Resource<APIResponse> {
        return newsRepository.getTechRelatedArticles(category, source, page)
    }


//    suspend fun execute(category: String, source: String, page: Int): Flow<PagingData<Article>> {
//        val pageSize = 30
//        return Pager(
//                config = PagingConfig(pageSize = pageSize),
//                pagingSourceFactory = {
//                    NewsDataSource(newsRepository, category, source, pageSize)
//                }
//        ).flow
//    }

}


//class NewsDataSource(
//        private val newsRepository: NewsRepository,
//        private val category: String,
//        private val source: String,
//        private val pageSize: Int
//) : PagingSource<Int, Article>() {
//
//    private var totalPages = 1
//
//    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
//        return state.anchorPosition?.let { position ->
//            state.closestPageToPosition(position)?.prevKey?.plus(1)
//                    ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
//        val pageNumber = params.key ?: 1
//
//        return try {
//            val response = newsRepository.getTechRelatedArticles(category, source, 1, pageSize)
//            totalPages = response.data?.totalResults ?: totalPages
//
//
//            val nextKey = if (totalPages < response.data?.totalResults ?: totalPages) {
//                pageNumber + (params.loadSize / pageSize)
//            } else {
//                null
//            }
//
//            Timber.e("next key $nextKey")
//
//            LoadResult.Page(
//                    data = response.data!!.articles,
//                    prevKey = params.key,
//                    nextKey = nextKey
//            )
//
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }

