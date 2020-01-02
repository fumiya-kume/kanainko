package kuu.nagoya.feature.result

import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase
import kuu.nagoya.feature.result.usecase.PredictAudioUsecase
import kuu.nagoya.feature.result.usecase.impl.LoadRecordResultUsecaseImpl
import kuu.nagoya.feature.result.usecase.impl.PredictAudioUsecaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {
    factory { LoadRecordResultUsecaseImpl(get()) as LoadRecordResultUsecase }
    factory { PredictAudioUsecaseImpl(androidApplication()) as PredictAudioUsecase }

    // Viewmodel
    viewModel { ResultFragmentViewModel(get()) }
}
