package kuu.nagoya.feature.record.legacy.domain.usecase

import kuu.nagoya.feature.record.legacy.domain.model.SoundRawDataModel
import kotlinx.coroutines.flow.Flow

internal interface LoadSoundRawDataUsecase {
    suspend fun execute(): Flow<SoundRawDataModel>
}
