package kuu.nagoya.feature.result

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourierService
import kuu.nagoya.feature.result.usecase.RecordDataToUserVoiceDataUsecase

internal class UserVoiceSpectrogramLiveData(
    private val fourierService: FourierService,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val recordDataToUserVoiceDataUsecase: RecordDataToUserVoiceDataUsecase,
    coroutineScope: CoroutineScope
) : LiveData<Bitmap>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            val recordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
            postValue(
                fourierService.audioDataToImage(
                    recordDataToUserVoiceDataUsecase.execute(recordData)
                )
            )
        }
    }
}