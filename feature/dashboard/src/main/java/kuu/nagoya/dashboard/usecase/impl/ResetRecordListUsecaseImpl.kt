package kuu.nagoya.dashboard.usecase.impl


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