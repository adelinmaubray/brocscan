package be.ami.maubray.brocscan

import brocscan.shared.generated.resources.Res
import java.net.URI
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

class JvmSoundPlayer : SoundPlayer {
    private var clip: Clip? = null

    override fun play(sound: Sound) {
        runCatching {
            val url = URI(Res.getUri(sound.path)).toURL()
            clip?.close()
            clip = AudioSystem.getClip().also { clip ->
                clip.open(AudioSystem.getAudioInputStream(url))
                clip.start()
            }
        }
    }
}

actual fun createSoundPlayer(): SoundPlayer = JvmSoundPlayer()
