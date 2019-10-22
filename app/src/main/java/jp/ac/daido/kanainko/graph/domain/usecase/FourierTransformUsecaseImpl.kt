package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.FourierTransformationModel
import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel
import jp.ac.daido.kanainko.record.domain.usecase.FourierTransformUsecase

internal class FourierTransformUsecaseImpl : FourierTransformUsecase {
    override suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel {
        return FourierTransformationModel(emptyList())
    }
}