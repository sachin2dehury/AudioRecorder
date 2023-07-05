package github.sachin2dehury.audiorecorder.record

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File
import java.io.FileOutputStream

class AudioRecorderImpl(
    private val context: Context
) : AudioRecorder {

    private var mediaRecorder: MediaRecorder? = null

    override fun start(file: File) {
        mediaRecorder = createMediaRecorder().apply {
            // order of op format & encoder matters
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(file).fd)

            prepare()
            start()
        }
    }

    override fun stop() {
        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null
    }

    private fun createMediaRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }
}