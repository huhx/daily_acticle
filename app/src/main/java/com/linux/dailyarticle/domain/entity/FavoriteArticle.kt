package com.linux.dailyarticle.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_article")
data class FavoriteArticle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val title: String,
    val digest: String,
    val status: Boolean,
    @ColumnInfo(name = "create_time") val createTime: Long
)