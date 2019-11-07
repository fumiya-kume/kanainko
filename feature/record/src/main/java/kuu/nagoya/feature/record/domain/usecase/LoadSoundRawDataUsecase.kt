package kuu.nagoya.feature.record.domain.usecase

import kuu.nagoya.feature.record.domain.model.SoundRawDataModel
import kotlinx.coroutines.flow.Flow

internal interface LoadSoundRawDataUsecase {
    suspend fun execute(): Flow<SoundRawDataModel>
}
