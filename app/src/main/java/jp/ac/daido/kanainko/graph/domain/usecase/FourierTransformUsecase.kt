package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.graph.domain.model.FourierTransformationModel
import jp.ac.daido.kanainko.graph.domain.model.SoundRawDataModel

internal interface FourierTransformUsecase {
    suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel
}