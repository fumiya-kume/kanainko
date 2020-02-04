package kuu.nagoya.feature.result

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourierService
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase

internal class ModelVoiceSpectrogramLiveData(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val fourierService: FourierService,
    private val recordDataToModelVoiceDataUsecase: RecordDataToModelVoiceDataUsecase,
    coroutineScope: CoroutineScope
) : LiveData<Bitmap>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            val recordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
            val voice = recordDataToModelVoiceDataUsecase.execute(recordData)
            postValue(
                fourierService.audioDataToImage(voice)
            )
        }
    }
}