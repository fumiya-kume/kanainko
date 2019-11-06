package kuu.nagoya.dashboard.usecase.impl

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.usecase.PlayAudioUsecase

internal class PlayAudioUsecaseImpl(
    private val context: Context
) : PlayAudioUsecase {
    override suspend fun execute(record: Record) {
        val mediaPlayer = MediaPlayer.create(
            context,
            record.filePath.path.toUri()
        )
        mediaPlayer.start()
    }
}