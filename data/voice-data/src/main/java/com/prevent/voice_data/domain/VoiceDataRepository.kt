package com.prevent.voice_data.domain

import java.io.File

interface VoiceDataRepository {
    suspend fun loadModelVoiceByName(name: String): File
}