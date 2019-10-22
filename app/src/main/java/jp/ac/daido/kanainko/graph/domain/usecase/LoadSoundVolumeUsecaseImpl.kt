package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.SoundVolumeModel
import jp.ac.daido.kanainko.record.domain.model.convert
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import jp.ac.daido.kanainko.record.domain.usecase.LoadSoundVolumeUsecase
import kotlinx.coroutines.flow.Flow

internal class LoadSoundVolumeUsecaseImpl(
    private val audioRepository: AudioRepository
) : LoadSoundVolumeUsecase {
    override suspend fun execute(): Flow<SoundVolumeModel> =
        audioRepository.loadAudioData().mapIndexed { index, value ->
            value.average().toFloat().convert(index)
        }
}
