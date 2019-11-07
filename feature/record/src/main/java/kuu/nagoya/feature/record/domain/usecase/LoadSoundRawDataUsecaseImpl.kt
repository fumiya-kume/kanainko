package kuu.nagoya.feature.record.domain.usecase

import kotlinx.coroutines.flow.Flow
import kuu.nagoya.feature.record.domain.model.SoundRawDataModel
import kuu.nagoya.feature.record.domain.model.convert
import kuu.nagoya.feature.record.domain.repository.AudioRepository

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
