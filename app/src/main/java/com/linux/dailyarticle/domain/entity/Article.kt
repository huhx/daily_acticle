package com.linux.dailyarticle.domain.entity

data class Article(
    val date: String,
    val author: String,
    val title: String,
    val digest: String,
    val content: String,
    val words: String
)