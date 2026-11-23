package be.ami.maubray.brocscan

import brocscan.shared.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioPlayer
import platform.Foundation.NSURL

class IOSSoundPlayer : SoundPlayer {
    private var player: AVAudioPlayer? = null

    @OptIn(ExperimentalForeignApi::class)
	override fun play(sound: Sound) {
        val url = NSURL.URLWithString(Res.getUri(sound.path)) ?: return
        player = AVAudioPlayer(contentsOfURL = url, error = null)
        player?.prepareToPlay()
        player?.play()
    }
}

actual fun createSoundPlayer(): SoundPlayer = IOSSoundPlayer()
