package kuu.nagoya.feature.record.view

import kuu.nagoya.feature.record.usecase.CreateTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.RestartTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.SaveTmpRecordUsecase

internal class TmpRecordLiveDataFactory(
    private val createTmpRecordUsecase: CreateTmpRecordUsecase,
    private val restartTmpRecordUsecase: RestartTmpRecordUsecase,
    private val saveTmpRecordUsecase: SaveTmpRecordUsecase
) {
    fun create(): TmpRecordLiveData {
        return TmpRecordLiveData(
            createTmpRecordUsecase,
            restartTmpRecordUsecase,
            saveTmpRecordUsecase
        )
    }
}