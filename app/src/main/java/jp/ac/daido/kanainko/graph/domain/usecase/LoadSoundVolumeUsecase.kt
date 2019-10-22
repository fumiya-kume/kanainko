package jp.ac.daido.kanainko.graph.domain.usecase

import jp.ac.daido.kanainko.record.domain.model.SoundVolumeModel
import kotlinx.coroutines.flow.Flow

internal interface LoadSoundVolumeUsecase {
    suspend fun execute(): Flow<SoundVolumeModel>
}
