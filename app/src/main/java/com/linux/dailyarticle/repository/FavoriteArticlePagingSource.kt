package com.linux.dailyarticle.repository

import androidx.paging.PagingSource
import com.linux.dailyarticle.db.dao.ArticleDao
import com.linux.dailyarticle.domain.entity.FavoriteArticle

private const val PAGE_INDEX = 1

class FavoriteArticlePagingSource(private val articleDao: ArticleDao) : PagingSource<Int, FavoriteArticle>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteArticle> {
        val page = params.key ?: PAGE_INDEX
        val users = articleDao.getFavoriteArticles()
        return LoadResult.Page(
            data = users,
            prevKey = if (page == PAGE_INDEX) null else page - 1,
            nextKey = if (page == users.size) null else page + 1
        )
    }
}