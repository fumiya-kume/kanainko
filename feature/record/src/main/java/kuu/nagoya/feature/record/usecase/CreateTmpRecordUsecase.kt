package kuu.nagoya.feature.record.usecase

import kuu.nagoya.feature.record.entity.TmpRecord

internal interface CreateTmpRecordUsecase {
    suspend fun execute(): TmpRecord
}