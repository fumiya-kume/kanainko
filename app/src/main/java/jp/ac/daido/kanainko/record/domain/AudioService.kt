package jp.ac.daido.kanainko.record.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

internal interface AudioService {

    suspend fun load(): Flow<List<Float>>
}