package kuu.nagoya.dashboard.usecase.impl

import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.entity.toFilePath
import kuu.nagoya.dashboard.entity.toRecordName
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase


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
                ),
                Record(
                    2,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                ),
                Record(
                    3,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                ),
                Record(
                    4,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                ),
                Record(
                    5,
                    "aaa".toFilePath(),
                    "名前".toRecordName()
                )
            )
        )
    }
}