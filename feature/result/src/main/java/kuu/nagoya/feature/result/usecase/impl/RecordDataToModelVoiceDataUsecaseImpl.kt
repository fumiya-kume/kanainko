package kuu.nagoya.feature.result.usecase.impl

import com.prevent.voice_data.domain.VoiceDataRepository
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase
import kuu.nagoya.waveparser.WaveParse

class RecordDataToModelVoiceDataUsecaseImpl(
    private val voiceDataRepository: VoiceDataRepository
) : RecordDataToModelVoiceDataUsecase {
    override suspend fun execute(recordData: TmpRecordDataEntity): List<Short> {
        val voiceFile = voiceDataRepository.loadModelVoiceByName("P${recordData.id.toString().padStart(
            3,
            '0'
        )}.wav")
        return WaveParse.loadWaveFromFile(voiceFile).data
    }
}