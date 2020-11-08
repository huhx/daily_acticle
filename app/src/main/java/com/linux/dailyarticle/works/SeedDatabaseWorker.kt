package com.linux.dailyarticle.works

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.linux.dailyarticle.db.AppDatabase
import com.linux.dailyarticle.domain.entity.RelationArticle
import com.linux.dailyarticle.util.Constant.ARTICLE_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(ARTICLE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val deliveryType = object : TypeToken<List<RelationArticle>>() {}.type
                    val relationArticles: List<RelationArticle> = Gson().fromJson(jsonReader, deliveryType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.articleDao().insertAll(relationArticles)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Timber.tag(TAG).e(ex, "Error seeding database")
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}