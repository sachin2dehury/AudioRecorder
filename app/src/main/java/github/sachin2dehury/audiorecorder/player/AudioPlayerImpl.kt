package github.sachin2dehury.audiorecorder.player

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.core.net.toUri
import java.io.File

class AudioPlayerImpl(private val context: Context) : AudioPlayer {

    private var mediaPlayer: MediaPlayer? = null

    override fun play(file: File) {
        mediaPlayer = MediaPlayer.create(context, file.toUri()).apply {
            // calling prepare will crash
            start()
        }
    }

    override fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}