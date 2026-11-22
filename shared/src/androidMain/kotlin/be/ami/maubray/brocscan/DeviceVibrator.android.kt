package be.ami.maubray.brocscan

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.VibratorManager

object AndroidVibratorContext {
    lateinit var applicationContext: Context
}

class AndroidDeviceVibrator(context: Context) : DeviceVibrator {
    private val vibrator: android.os.Vibrator? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            context.getSystemService(VibratorManager::class.java)?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? android.os.Vibrator
        }
    }

    override val isSupported: Boolean
        get() = vibrator?.hasVibrator() == true

    override fun vibrate(durationMillis: Long) {
        val vibrator = vibrator ?: return
        if (!vibrator.hasVibrator()) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(durationMillis, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(durationMillis)
        }
    }

    override fun cancel() {
        vibrator?.cancel()
    }
}

actual fun createDeviceVibrator(): DeviceVibrator = AndroidDeviceVibrator(AndroidVibratorContext.applicationContext)
