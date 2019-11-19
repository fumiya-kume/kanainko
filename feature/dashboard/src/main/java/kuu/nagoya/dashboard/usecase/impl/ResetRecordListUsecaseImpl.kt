package kuu.nagoya.dashboard.usecase.impl

import android.content.Context
import kuu.nagoya.dashboard.entity.Record
import kuu.nagoya.dashboard.entity.toFilePath
import kuu.nagoya.dashboard.entity.toRecordName
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.dashboard.usecase.ResetRecordListUsecase


internal class ResetRecordListUsecaseImpl(
    private val recordListRepository: RecordListRepository,
    private val context:Context
) : ResetRecordListUsecase {
    override suspend fun execite() {

        val file = context.externalMediaDirs?.first()
        val filePath = "${file?.path}/output.wav"

        recordListRepository.storeRecordList(
            listOf(
                Record(
                    0,
                    filePath.toFilePath(),
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