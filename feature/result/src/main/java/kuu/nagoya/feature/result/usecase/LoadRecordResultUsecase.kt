package kuu.nagoya.feature.result.usecase

import kuu.nagoya.feature.result.entity.RecordResult

internal interface LoadRecordResultUsecase {
    suspend fun execute(): RecordResult
}