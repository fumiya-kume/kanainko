package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.graph.domain.model.FourierTransformationModel
import jp.ac.daido.kanainko.graph.domain.model.SoundRawDataModel

internal class FourierTransformUsecaseImpl : FourierTransformUsecase {
    override suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel {
        return FourierTransformationModel(emptyList())
    }
}