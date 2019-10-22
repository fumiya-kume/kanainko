package jp.ac.daido.kanainko.record.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AudioRepository {
    suspend fun loadAudioData(): Flow<List<Float>>
    suspend fun startAudioRecord(): Unit
}
