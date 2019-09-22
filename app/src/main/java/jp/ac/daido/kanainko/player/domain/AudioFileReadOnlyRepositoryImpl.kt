package jp.ac.daido.kanainko.player.domain

import jp.ac.daido.kanainko.player.domain.model.AudioFileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

internal class AudioFileReadOnlyRepositoryImpl: AudioFileReadOnlyRepository {
    override fun loadAudioFileList(): Flow<List<AudioFileModel>?> {
        return flow {
            val path = "sdcard" + "/kanainko"

            val fileList =
                File(path)
                    .listFiles()
                    ?.mapIndexed { index: Int, file: File -> AudioFileModel(index, file.name) }

            this.emit(fileList)
        }
    }
}