package com.linux.dailyarticle.api.response.article

data class ArticleData(
    val author: String,
    val content: String,
    val date: ArticleDate,
    val digest: String,
    val title: String,
    val wc: Int
)