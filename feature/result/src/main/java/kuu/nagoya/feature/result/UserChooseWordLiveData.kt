package kuu.nagoya.feature.result

import androidx.lifecycle.LiveData
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository

internal class UserChooseWordLiveData(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) : LiveData<String>() {
    init {
        val tmpRecordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
        postValue(
            tmpRecordData.chooseCharacter.toString()
        )
    }
}