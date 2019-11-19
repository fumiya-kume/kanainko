package kuu.nagoya.feature.record.legacy.domain.usecase

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.feature.record.legacy.domain.model.SoundRawDataModel

internal interface LoadSoundRawDataUsecase {
    suspend fun execute(): Flow<SoundRawDataModel>
}
