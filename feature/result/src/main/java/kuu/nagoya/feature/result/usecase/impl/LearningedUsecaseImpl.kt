package kuu.nagoya.feature.result.usecase.impl

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.usecase.LearningedUsecase
import nagoya.kuu.learning_data.LearningDataRepository

class LearningedUsecaseImpl(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val learningDataRepository: LearningDataRepository
) : LearningedUsecase {
    override fun execute() {
        val tmpRecordDataEntity = tmpRecordDataReadonlyRepository.loadTmpRecordData()
        learningDataRepository.uddateLearnedStatus(tmpRecordDataEntity.id, true)
    }
}