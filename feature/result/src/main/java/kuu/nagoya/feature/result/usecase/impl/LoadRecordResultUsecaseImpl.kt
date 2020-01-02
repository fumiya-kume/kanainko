package kuu.nagoya.feature.result.usecase.impl

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.entity.RecordResult
import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase

internal class LoadRecordResultUsecaseImpl(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) :
    LoadRecordResultUsecase {
    override suspend fun execute(): RecordResult {
        val tmpRecordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
        return RecordResult(
            tmpRecordData.id,
            tmpRecordData.audioFilePath ?: "",
            tmpRecordData.chooseCharacter ?: 'n'
        )
    }
}
