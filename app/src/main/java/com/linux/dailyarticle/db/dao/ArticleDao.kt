package com.linux.dailyarticle.db.dao

import androidx.room.*
import com.linux.dailyarticle.domain.entity.FavoriteArticle
import com.linux.dailyarticle.domain.entity.RelationArticle

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(relationArticles: List<RelationArticle>)

    @Query("SELECT * FROM FAVORITE_ARTICLE")
    fun getFavoriteArticles(): List<FavoriteArticle>


    @Query("SELECT old_date FROM RELATION_ARTICLE where new_date = :date")
    fun getRelationDate(date: String): String

    @Query("SELECT * FROM favorite_article where date = :date")
    suspend fun getFavorite(date: String): FavoriteArticle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteArticle: FavoriteArticle)

    @Update
    suspend fun updateFavorite(favoriteArticle: FavoriteArticle)
}