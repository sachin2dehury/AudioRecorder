package github.sachin2dehury.audiorecorder.player

import java.io.File

interface AudioPlayer {
    fun play(file: File)
    fun stop()
}