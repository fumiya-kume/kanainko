package kuu.nagoya.feature.result

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository

internal class UserChooseWordLiveDataFactory(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) {
    fun create(): UserChooseWordLiveData {
        return UserChooseWordLiveData(
            tmpRecordDataReadonlyRepository
        )
    }
}