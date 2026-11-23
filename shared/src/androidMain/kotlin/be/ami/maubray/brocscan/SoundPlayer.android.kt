package be.ami.maubray.brocscan

import android.content.Context
import android.media.MediaPlayer

private const val COMPOSE_RESOURCES_ASSET_DIR = "composeResources/brocscan.shared.generated.resources/"

class AndroidSoundPlayer(private val context: Context) : SoundPlayer {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(sound: Sound) {
        mediaPlayer?.release()
        context.assets.openFd(COMPOSE_RESOURCES_ASSET_DIR + sound.path).use { fd ->
            mediaPlayer = MediaPlayer().apply {
                setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
                prepare()
                setOnCompletionListener { release() }
                start()
            }
        }
    }
}

actual fun createSoundPlayer(): SoundPlayer = AndroidSoundPlayer(AndroidVibratorContext.applicationContext)
