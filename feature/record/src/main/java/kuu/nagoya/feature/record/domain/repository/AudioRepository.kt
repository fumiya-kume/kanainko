package kuu.nagoya.feature.record.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AudioRepository {
    val audioFlow: Flow<List<Float>>
    val audioMaxAmplitudeFlow: Flow<Float>
}
