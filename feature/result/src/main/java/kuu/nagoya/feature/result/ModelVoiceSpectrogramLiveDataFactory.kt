package kuu.nagoya.feature.result

import kotlinx.coroutines.CoroutineScope
import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.feature.result.service.FourierService
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase

internal class ModelVoiceSpectrogramLiveDataFactory(
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository,
    private val fourierService: FourierService,
    private val recordDataToModelVoiceDataUsecase: RecordDataToModelVoiceDataUsecase
) {
    fun create(
        coroutineScope: CoroutineScope
    ): ModelVoiceSpectrogramLiveData {
        return ModelVoiceSpectrogramLiveData(
            tmpRecordDataReadonlyRepository,
            fourierService,
            recordDataToModelVoiceDataUsecase,
            coroutineScope
        )
    }
}