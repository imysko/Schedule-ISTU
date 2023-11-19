package com.istu.schedule.ui.util

import android.content.Context
import android.os.Build
import android.os.CombinedVibration
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import javax.inject.Inject

interface VibrationManager {

    fun vibrate(context: Context, effect: VibrationEffect)

    companion object VibrationEffects {
        val LongPress: VibrationEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        } else {
            VibrationEffect.createOneShot(25, VibrationEffect.DEFAULT_AMPLITUDE)
        }
    }
}

class VibrationManagerImpl @Inject constructor() : VibrationManager {

    @Suppress("DEPRECATION")
    override fun vibrate(context: Context, effect: VibrationEffect) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager

            vibratorManager.cancel()
            vibratorManager.vibrate(CombinedVibration.createParallel(effect))
        } else {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            vibrator.cancel()
            vibrator.vibrate(effect)
        }
    }
}