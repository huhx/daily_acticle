package com.linux.dailyarticle.util

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator

object SystemUtil {
    fun vibration(context: Context, milliseconds: Long) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE))

    }
}