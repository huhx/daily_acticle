package com.linux.dailyarticle.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linux.dailyarticle.domain.entity.FavoriteArticle
import com.linux.dailyarticle.domain.entity.RelationArticle

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(relationArticles: List<RelationArticle>)

    @Query("SELECT * FROM FAVORITE_ARTICLE")
    suspend fun getFavoriteArticles(): List<FavoriteArticle>
}