package kuu.nagoya.feature.record.domain.usecase

import kuu.nagoya.feature.record.domain.model.SoundVolumeModel
import kotlinx.coroutines.flow.Flow

internal interface LoadSoundVolumeUsecase {
    suspend fun execute(): Flow<SoundVolumeModel>
}
