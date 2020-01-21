package kuu.nagoya.dashboard.usecase.impl

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.core.net.toUri
import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.usecase.PlayAudioUsecase

internal class PlayAudioUsecaseImpl(
    private val context: Context
) : PlayAudioUsecase {
    override suspend fun execute(record: Record) {

        val mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(context, "file:///${record.filePath.path.toUri()}".toUri())
        mediaPlayer.setOnPreparedListener {
            it.start()
        }

        try {
            mediaPlayer.prepare()
        } catch (e: Exception) {
            Log.d("hello", e.toString())
        }
    }
}
