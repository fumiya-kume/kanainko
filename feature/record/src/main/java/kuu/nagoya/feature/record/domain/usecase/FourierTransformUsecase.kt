package kuu.nagoya.feature.record.domain.usecase

import kuu.nagoya.feature.record.domain.model.FourierTransformationModel
import kuu.nagoya.feature.record.domain.model.SoundRawDataModel

internal interface FourierTransformUsecase {
    suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel
}