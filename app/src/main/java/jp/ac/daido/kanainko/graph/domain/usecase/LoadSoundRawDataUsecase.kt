package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.graph.domain.model.SoundRawDataModel
import kotlinx.coroutines.flow.Flow

internal interface LoadSoundRawDataUsecase {
    suspend fun execute(): Flow<SoundRawDataModel>
}
