package kuu.nagoya.feature.result

import androidx.lifecycle.LiveData
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository

internal class UserChooseWordLiveData(
    tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) : LiveData<String>() {
    init {
        kotlin.runCatching {
            tmpRecordDataReadonlyRepository.loadTmpRecordData()
        }.onSuccess {
            postValue(it.chooseCharacter.toString())
        }.onFailure {
            postValue("Can not read string")
        }
    }
}