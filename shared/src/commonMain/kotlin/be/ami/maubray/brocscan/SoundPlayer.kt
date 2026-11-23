package be.ami.maubray.brocscan

enum class Sound(val path: String) {
    RIGHT("files/sounds/right.wav"),
    WRONG("files/sounds/wrong.mp3"),
}

fun interface SoundPlayer {
    fun play(sound: Sound)
}

expect fun createSoundPlayer(): SoundPlayer
