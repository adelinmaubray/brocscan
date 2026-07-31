package be.ami.maubray.brocscan

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform