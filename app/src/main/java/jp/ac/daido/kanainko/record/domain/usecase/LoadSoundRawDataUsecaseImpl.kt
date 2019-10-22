package jp.ac.daido.kanainko.record.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel
import jp.ac.daido.kanainko.record.domain.model.convert
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow

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
