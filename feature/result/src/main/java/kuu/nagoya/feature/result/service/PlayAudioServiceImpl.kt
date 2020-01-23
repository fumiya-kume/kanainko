package kuu.nagoya.feature.result.service

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.net.Uri

class PlayAudioServiceImpl(
    private val context: Context
) : PlayAudioService {
    override suspend fun playAudio(path: String) {
        val mediaPlater = MediaPlayer().apply {
            this.setDataSource(context, Uri.parse(path))
            this.prepare()
            start()
        }
    }

    override suspend fun playAudio(fileDescriptor: AssetFileDescriptor) {
        val mediaPlater = MediaPlayer().apply {
            setDataSource(fileDescriptor)
            prepare()
            start()
        }
    }
}