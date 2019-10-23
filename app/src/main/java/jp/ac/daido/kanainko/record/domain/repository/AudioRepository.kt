package jp.ac.daido.kanainko.record.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AudioRepository {
    val audioFlow: Flow<List<Float>>
}
