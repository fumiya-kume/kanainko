package kuu.nagoya.feature.record.legacy.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface AudioRepository {
    val audioFlow: Flow<List<Float>>
    val audioMaxAmplitudeFlow: Flow<Float>
}
