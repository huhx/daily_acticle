package com.linux.dailyarticle.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "relation_article")
data class RelationArticle(
    @ColumnInfo(name = "old_date") @PrimaryKey val oldDate: String,

    @ColumnInfo(name = "new_date") val newDate: String,
)