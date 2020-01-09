package kuu.nagoya.feature.result

import kuu.nagoya.feature.result.service.AssetsService
import kuu.nagoya.feature.result.service.AssetsServiceImpl
import kuu.nagoya.feature.result.service.FourieService
import kuu.nagoya.feature.result.service.FourieServiceImpl
import kuu.nagoya.feature.result.service.PlayAudioService
import kuu.nagoya.feature.result.service.PlayAudioServiceImpl
import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import kuu.nagoya.feature.result.usecase.impl.LoadRecordResultUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PredictAudioUsecaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {
    factory { LoadRecordResultUsecaseImpl(get()) as LoadRecordResultUsecase }
    factory { PredictAudioUsecaseImpl(androidApplication()) as PredictAudioUsecase }
    factory { FourieServiceImpl() as FourieService }
    factory { PlayAudioServiceImpl(get()) as PlayAudioService }
    factory { AssetsServiceImpl(androidContext()) as AssetsService }

    // Viewmodel
    viewModel { ResultFragmentViewModel(get(), get(), get(), get()) }
}
