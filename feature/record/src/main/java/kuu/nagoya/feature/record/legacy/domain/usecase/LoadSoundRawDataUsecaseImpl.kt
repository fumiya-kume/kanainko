package kuu.nagoya.feature.record.legacy.domain.usecase

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.feature.record.legacy.domain.model.SoundRawDataModel
import kuu.nagoya.feature.record.legacy.domain.model.convert
import kuu.nagoya.feature.record.legacy.domain.repository.AudioRepository

internal class LoadSoundRawDataUsecaseImpl(
    private val audioRepository: AudioRepository
) : LoadSoundRawDataUsecase {

    override suspend fun execute(): Flow<SoundRawDataModel> {
        return audioRepository
            .audioFlow
            .mapIndexed { index, value ->
                value.convert(
                    index
                )
            }
    }
}
