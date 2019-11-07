package kuu.nagoya.feature.record.legacy.domain.usecase

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.feature.record.legacy.domain.model.SoundVolumeModel
import kuu.nagoya.feature.record.legacy.domain.model.convert
import kuu.nagoya.feature.record.legacy.domain.repository.AudioRepository

internal class LoadSoundVolumeUsecaseImpl(
    private val audioRepository: AudioRepository
) : LoadSoundVolumeUsecase {
    override suspend fun execute(): Flow<SoundVolumeModel> =
        audioRepository
            .audioFlow
            .mapIndexed { index, value ->
                value.average().toFloat().convert(index)
            }
}
