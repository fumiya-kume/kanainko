package kuu.nagoya.feature.result.service

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri

class PlayAudioServiceImpl(
    private val context: Context
) : PlayAudioService {
    override suspend fun playAudio(path: String) {
        val mediaPlater = MediaPlayer()
        mediaPlater.setDataSource(context, Uri.parse(path))
        mediaPlater.prepare()
        mediaPlater.start()
    }

    override suspend fun playAudio(fileDescriptor: AssetFileDescriptor) {
        val mediaPlater = MediaPlayer()
        mediaPlater.setDataSource(fileDescriptor)
        mediaPlater.prepare()
        mediaPlater.start()
    }
}