package kuu.nagoya.feature.record.legacy.domain.usecase

import kuu.nagoya.feature.record.legacy.domain.model.FourierTransformationModel
import kuu.nagoya.feature.record.legacy.domain.model.SoundRawDataModel

internal interface FourierTransformUsecase {
    suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel
}