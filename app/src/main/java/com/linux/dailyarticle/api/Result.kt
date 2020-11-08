package com.linux.dailyarticle.api

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()

    data class Error(val exception: String) : Result<Nothing>()
}