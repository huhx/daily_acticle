package com.linux.dailyarticle.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DATE_FORMAT = "yyyyMMdd"

    private fun format(date: Date?, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    }

    fun dateFormat(date: Date?): String {
        return format(date, DATE_FORMAT)
    }

    fun currentArticle(): String {
        return format(Date(), DATE_FORMAT)
    }

    fun plusDays(date: Date?, day: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DATE, day)
        return cal.time
    }

    fun isAfter(src: Date, dest: Date): Boolean {
        return removeTime(src).after(removeTime(dest))
    }

    private fun removeTime(date: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        cal[Calendar.HOUR_OF_DAY] = 0
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        cal[Calendar.MILLISECOND] = 0
        return cal.time
    }
}