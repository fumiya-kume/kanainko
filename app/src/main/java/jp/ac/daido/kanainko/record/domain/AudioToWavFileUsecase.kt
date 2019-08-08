package jp.ac.daido.kanainko.record.domain

import jp.ac.daido.kanainko.record.domain.entity.AudioFileConfigEntity

interface AudioToWavFileUsecase {
    suspend fun save(fileName: String, audioFileConfig: AudioFileConfigEntity)
}

