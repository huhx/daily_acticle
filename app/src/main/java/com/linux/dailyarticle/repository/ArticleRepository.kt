package com.linux.dailyarticle.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.linux.dailyarticle.api.ArticleApi
import com.linux.dailyarticle.api.response.article.ArticleResp
import com.linux.dailyarticle.db.dao.ArticleDao
import com.linux.dailyarticle.domain.entity.FavoriteArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val articleDao: ArticleDao, private val articleApi: ArticleApi) {

    fun getFavoriteArticleStream(): Flow<PagingData<FavoriteArticle>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { FavoriteArticlePagingSource(articleDao) }
        ).flow
    }

    suspend fun getArticle(date: String): ArticleResp {
        val relationDate = articleDao.getRelationDate(date)
        return articleApi.getArticleByDate(relationDate)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}