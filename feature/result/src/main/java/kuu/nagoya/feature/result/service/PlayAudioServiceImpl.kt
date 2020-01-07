package kuu.nagoya.feature.result.service

import android.media.MediaPlayer

class PlayAudioServiceImpl : PlayAudioService {
    override suspend fun playAudio(path: String) {
        val mediaPlater = MediaPlayer()
        mediaPlater.setDataSource(path)
        mediaPlater.prepare()
        mediaPlater.start()
    }
}