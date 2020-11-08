package com.linux.dailyarticle.api

import com.linux.dailyarticle.api.response.ArticleResp
import com.linux.dailyarticle.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET
    suspend fun getArticleByDate(@Query("date") date: String): ArticleResp

    companion object {
        fun create(): ArticleApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleApi::class.java)
        }
    }
}