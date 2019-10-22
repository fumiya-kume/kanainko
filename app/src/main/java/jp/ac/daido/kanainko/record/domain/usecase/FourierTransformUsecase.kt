package jp.ac.daido.kanainko.record.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.FourierTransformationModel
import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel

internal interface FourierTransformUsecase {
    suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel
}