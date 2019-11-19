package kuu.nagoya.feature.record.legacy.domain.usecase

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.feature.record.legacy.domain.model.SoundVolumeModel

internal interface LoadSoundVolumeUsecase {
    suspend fun execute(): Flow<SoundVolumeModel>
}
