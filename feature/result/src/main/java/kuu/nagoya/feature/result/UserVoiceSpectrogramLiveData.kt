package kuu.nagoya.feature.result

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.usecase.RecordDataToUserVoiceDataUsecase

internal class UserVoiceSpectrogramLiveData(
    private val fourieService: FourieService,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val recordDataToUserVoiceDataUsecase: RecordDataToUserVoiceDataUsecase,
    private val coroutineScope: CoroutineScope
) : LiveData<Bitmap>() {
    init {
        coroutineScope.launch(Dispatchers.IO) {
            val recordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
            postValue(
                fourieService.audioDataToImage(
                    recordDataToUserVoiceDataUsecase.execute(recordData)
                )
            )
        }
    }
}