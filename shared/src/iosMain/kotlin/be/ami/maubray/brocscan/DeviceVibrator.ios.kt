package be.ami.maubray.brocscan

import platform.AudioToolbox.AudioServicesPlaySystemSound
import platform.UIKit.UIDevice

private const val SYSTEM_SOUND_ID_VIBRATE: UInt = 4095u

class IOSDeviceVibrator : DeviceVibrator {
    override val isSupported: Boolean
        get() = UIDevice.currentDevice.model == "iPhone"

    override fun vibrate(durationMillis: Long) {
        if (!isSupported) return
        AudioServicesPlaySystemSound(SYSTEM_SOUND_ID_VIBRATE)
    }

    override fun cancel() {
		/* empty */
    }
}

actual fun createDeviceVibrator(): DeviceVibrator = IOSDeviceVibrator()
