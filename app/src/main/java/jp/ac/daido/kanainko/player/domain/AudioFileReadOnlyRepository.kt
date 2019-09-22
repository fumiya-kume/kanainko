package jp.ac.daido.kanainko.player.domain

import jp.ac.daido.kanainko.player.domain.model.AudioFileModel
import kotlinx.coroutines.flow.Flow

internal interface AudioFileReadOnlyRepository {
    fun loadAudioFileList(): Flow<List<AudioFileModel>?>
}