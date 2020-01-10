package kuu.nagoya.feature.result.usecase.impl

import java.io.File
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.feature.result.usecase.RecordDataToUserVoiceDataUsecase
import kuu.nagoya.waveparser.WaveParse

class RecordDataToUserVoiceDataUsecaseImpl :
    RecordDataToUserVoiceDataUsecase {
    override fun execute(tmpRecordDataEntity: TmpRecordDataEntity): List<Short> {
        return WaveParse.loadWaveFromFile(File(tmpRecordDataEntity.audioFilePath)).data
    }
}