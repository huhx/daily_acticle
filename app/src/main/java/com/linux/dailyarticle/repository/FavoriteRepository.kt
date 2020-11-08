package com.linux.dailyarticle.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.linux.dailyarticle.db.dao.ArticleDao
import com.linux.dailyarticle.domain.entity.FavoriteArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val articleDao: ArticleDao) {

    fun getFavoriteArticleStream(): Flow<PagingData<FavoriteArticle>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { FavoriteArticlePagingSource(articleDao) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}