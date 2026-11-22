package be.ami.maubray.brocscan

interface DeviceVibrator {
    val isSupported: Boolean
    fun vibrate(durationMillis: Long = 100L)
    fun cancel()
}

expect fun createDeviceVibrator(): DeviceVibrator
