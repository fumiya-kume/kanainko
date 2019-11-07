package kuu.nagoya.feature.record.view

import androidx.lifecycle.LiveData
import kuu.nagoya.feature.record.usecase.CreateTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.RestartTmpRecordUsecase
import kuu.nagoya.feature.record.usecase.SaveTmpRecordUsecase
import kuu.nagoya.feature.record.view.viewentity.TmpRecorderViewEntity
import kuu.nagoya.feature.record.view.viewentity.convert

internal sealed class TmpRecordStatus {
    data class Created(val tmpRecord: TmpRecorderViewEntity) : TmpRecordStatus()
    data class Restarted(val tmpRecord: TmpRecorderViewEntity) : TmpRecordStatus()
    object Saved : TmpRecordStatus()
    object StartTmpRecordFailed : TmpRecordStatus()
    object RestartTmpRecordFailed : TmpRecordStatus()
    object SavedTmpRecordFailed : TmpRecordStatus()
}

internal class TmpRecordLiveData(
    private val createTmpRecordUsecase: CreateTmpRecordUsecase,
    private val restartTmpRecordUsecase: RestartTmpRecordUsecase,
    private val saveTmpRecordUsecase: SaveTmpRecordUsecase
) : LiveData<TmpRecordStatus>() {

    suspend fun create() =
        kotlin.runCatching {
            createTmpRecordUsecase
                .execute()
        }.fold(
            onSuccess = {
                postValue(
                    TmpRecordStatus.Created(
                        it.convert()
                    )
                )
            },
            onFailure = {
                postValue(
                    TmpRecordStatus.StartTmpRecordFailed
                )
            }
        )

    suspend fun reStart() =
        runCatching {
            restartTmpRecordUsecase
                .execute()
        }.fold(
            onSuccess = {
                postValue(
                    TmpRecordStatus.Restarted(
                        it.convert()
                    )
                )
            },
            onFailure = {
                postValue(
                    TmpRecordStatus.RestartTmpRecordFailed
                )
            }
        )

    suspend fun save() =
        runCatching {
            saveTmpRecordUsecase
                .execute()
        }.fold(
            onSuccess = {
                postValue(
                    TmpRecordStatus.Saved
                )
            },
            onFailure = {
                postValue(
                    TmpRecordStatus.SavedTmpRecordFailed
                )
            }
        )
}