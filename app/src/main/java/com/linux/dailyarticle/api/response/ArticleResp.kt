package com.linux.dailyarticle.api.response

data class ArticleResp(
    val date: String,
    val author: String,
    val title: String,
    val digest: String,
    val content: String,
    val words: Int
)