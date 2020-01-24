package kuu.nagoya.feature.result

import kuu.nagoya.feature.result.service.AssetsService
import kuu.nagoya.feature.result.service.AssetsServiceImpl
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.service.FourieServiceImpl
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.service.PlayAudioServiceImpl
import kuu.nagoya.feature.result.usecase.LearningedUsecase
import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase
import kuu.nagoya.feature.result.usecase.PlayModelVoiceUsecase
import kuu.nagoya.feature.result.usecase.PlayUserVoiceUsecase
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import kuu.nagoya.feature.result.usecase.PredictVoiceUsecase
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceDataUsecase
import kuu.nagoya.feature.result.usecase.RecordDataToModelVoiceFileUsecase
import kuu.nagoya.feature.result.usecase.RecordDataToUserVoiceDataUsecase
import kuu.nagoya.feature.result.usecase.impl.LearningedUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.LoadRecordResultUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PlayModelVoiceUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PlayUserVoiceUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PredictAudioUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PredictVoiceUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.RecordDataToModelVoiceDataUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.RecordDataToModelVoiceFileUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.RecordDataToUserVoiceDataUsecaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {

    factory { FourieServiceImpl() as FourieService }
    factory { PlayAudioServiceImpl(get()) as PlayAudioService }
    factory { AssetsServiceImpl(androidContext()) as AssetsService }

    // Usecase
    factory { LoadRecordResultUsecaseImpl(get()) as LoadRecordResultUsecase }
    factory { PredictAudioUsecaseImpl(androidApplication()) as PredictAudioUsecase }
    factory { PlayModelVoiceUsecaseImpl(get(), get(), get()) as PlayModelVoiceUsecase }
    factory { PlayUserVoiceUsecaseImpl(get(), get()) as PlayUserVoiceUsecase }
    factory { PredictVoiceUsecaseImpl() as PredictVoiceUsecase }
    factory { RecordDataToModelVoiceDataUsecaseImpl(get()) as RecordDataToModelVoiceDataUsecase }
    factory { RecordDataToModelVoiceFileUsecaseImpl(get()) as RecordDataToModelVoiceFileUsecase }
    factory { RecordDataToUserVoiceDataUsecaseImpl() as RecordDataToUserVoiceDataUsecase }
    factory { LearningedUsecaseImpl(get(), get()) as LearningedUsecase }

    // LiveData
    factory { ModelVoiceSpectrogramLiveDataFactory(get(), get(), get()) }
    factory { PredictWordLiveDataFactory(get(), get()) }
    factory { UserChooseWordLiveDataFactory(get()) }
    factory { UserVoiceSpectrogramLiveDataFactory(get(), get(), get()) }

    // Viewmodel
    viewModel { ResultFragmentViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
}
