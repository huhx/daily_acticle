package com.linux.dailyarticle.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun format(date: Date, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    }

    fun currentArticle(): String {
        return format(Date(), "yyyyMMdd")
    }

    fun getToDateAfterDays(date: Date?, day: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, day)
        return cal.time
    }
}