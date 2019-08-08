package jp.ac.daido.kanainko.record.infra

import jp.ac.daido.kanainko.record.domain.AudioToWavFileUsecase
import jp.ac.daido.kanainko.record.domain.entity.AudioFileConfigEntity

internal class AudioToWavFileUsecaseImpl : AudioToWavFileUsecase {
    override suspend fun save(fileName: String, audioFileConfig: AudioFileConfigEntity) {
        // 保存処理を書いていく
    }
}