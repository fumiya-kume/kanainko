package kuu.nagoya.feature.result.usecase.impl

import com.prevent.voice_data.domain.VoiceDataRepository
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase
import kuu.nagoya.waveparser.WaveParse

class RecordDataToModelVoiceDataUsecaseImpl(
    private val voiceDataRepository: VoiceDataRepository
) : RecordDataToModelVoiceDataUsecase {
    override suspend fun execite(recordData: TmpRecordDataEntity): List<Short> {
        val voiceFile = voiceDataRepository.loadModelVoiceByName("model_1.wav")
        return WaveParse.loadWaveFromFile(voiceFile).data
    }
}