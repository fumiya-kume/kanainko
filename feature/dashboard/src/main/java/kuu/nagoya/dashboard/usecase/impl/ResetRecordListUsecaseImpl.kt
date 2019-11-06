package kuu.nagoya.dashboard.usecase.impl

import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase
import kuu.nagoya.entity.recordentity.Record
import kuu.nagoya.entity.recordentity.toFilePath
import kuu.nagoya.entity.recordentity.toRecordName


internal class ResetRecordListUsecaseImpl(
    private val recordListRepository: RecordListRepository
) : ResetRecordListUsecase {
    override suspend fun execite() {
        recordListRepository.storeRecordList(
            listOf(
                Record(
                    0,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                ),
                Record(
                    1,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                )
            )
        )
    }
}