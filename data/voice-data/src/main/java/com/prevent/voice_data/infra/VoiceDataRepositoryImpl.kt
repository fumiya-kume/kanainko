package com.prevent.voice_data.infra

import android.content.Context
import com.prevent.voice_data.domain.VoiceDataRepository
import java.io.File

class VoiceDataRepositoryImpl(
    private val context: Context
) : VoiceDataRepository {
    override suspend fun loadModelVoiceByName(name: String): File {
        val mediaDir = context.externalMediaDirs.first()
        return File("${mediaDir.absolutePath}/model/$name")
    }
}