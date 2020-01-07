package kuu.nagoya.feature.result.service

interface PlayAudioService {
    suspend fun playAudio(path: String)
}