package jp.ac.daido.kanainko.graph.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AudioRepository {
    suspend fun loadAudioData(): Flow<List<Float>>
    suspend fun startAudioRecord(): Unit
}
