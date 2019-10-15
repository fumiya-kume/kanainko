package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.graph.domain.model.SoundRawDataModel
import jp.ac.daido.kanainko.graph.domain.model.convert
import jp.ac.daido.kanainko.graph.domain.repository.AudioRepository
import kotlinx.coroutines.flow.Flow

internal class LoadSoundRawDataUsecaseImpl(
    private val audioRepository: AudioRepository
) : LoadSoundRawDataUsecase {

    override suspend fun execute(): Flow<SoundRawDataModel> {

        return audioRepository.loadAudioData().mapIndexed { index, value -> value.convert(index) }
    }
}