package be.ami.maubray.brocscan

class NoOpDeviceVibrator : DeviceVibrator {
	
    override val isSupported: Boolean = false

    override fun vibrate(durationMillis: Long) {
		/* empty */
    }

    override fun cancel() {
		/* empty */
    }
}

actual fun createDeviceVibrator(): DeviceVibrator = NoOpDeviceVibrator()
