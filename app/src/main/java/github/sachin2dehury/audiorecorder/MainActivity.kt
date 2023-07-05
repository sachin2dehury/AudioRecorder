package github.sachin2dehury.audiorecorder

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import github.sachin2dehury.audiorecorder.player.AudioPlayer
import github.sachin2dehury.audiorecorder.player.AudioPlayerImpl
import github.sachin2dehury.audiorecorder.record.AudioRecorder
import github.sachin2dehury.audiorecorder.record.AudioRecorderImpl
import github.sachin2dehury.audiorecorder.ui.theme.AudioRecorderTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val recorder: AudioRecorder by lazy { AudioRecorderImpl(applicationContext) }

    private val player: AudioPlayer by lazy { AudioPlayerImpl(applicationContext) }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            1000
        )
        setContent {
            AudioRecorderTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Button(onClick = {
                        audioFile = File(cacheDir, "audio.mp3").also {
                            recorder.start(it)
                        }
                    }) {
                        Text("Start Record")
                    }

                    Button(onClick = {
                        recorder.stop()
                    }) {
                        Text("Stop Record")
                    }

                    Button(onClick = {
                        audioFile?.let { player.play(it) }
                    }) {
                        Text("Play")
                    }

                    Button(onClick = {
                        player.stop()
                    }) {
                        Text("Pause")
                    }
                }
            }
        }
    }
}
