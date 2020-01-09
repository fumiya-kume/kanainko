package kuu.nagoya.feature.result.service

import android.content.res.AssetFileDescriptor

interface PlayAudioService {
    suspend fun playAudio(path: String)
    suspend fun playAudio(fileDescriptor: AssetFileDescriptor)
}