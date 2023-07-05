package github.sachin2dehury.audiorecorder.record

import java.io.File

interface AudioRecorder {
    fun start(file: File)
    fun stop()
}