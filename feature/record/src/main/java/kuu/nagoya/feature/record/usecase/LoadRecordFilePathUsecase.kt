package kuu.nagoya.feature.record.usecase

import kuu.nagoya.feature.record.entity.RecordFilePath

internal interface LoadRecordFilePathUsecase {
    suspend fun execute(): RecordFilePath
}