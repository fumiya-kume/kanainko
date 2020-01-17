package kuu.nagoya.feature.result

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.usecase.PredictVoiceUsecase

internal class PredictWordLiveData(
    private val predictVoiceUsecase: PredictVoiceUsecase,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    coroutineScope: CoroutineScope
) : LiveData<String>() {
    init {
        coroutineScope.launch {
            kotlin.runCatching {
                predictVoiceUsecase.execute(
                    tmpRecordDataReadonlyRepository.loadTmpRecordData()
                )
            }.onSuccess {
                postValue(it)
            }.onFailure {
                postValue("認識失敗")
            }
        }
    }
}