package jp.ac.daido.kanainko.record.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.SoundVolumeModel
import jp.ac.daido.kanainko.record.domain.model.convert
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow

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
