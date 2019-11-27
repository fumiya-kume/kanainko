package kuu.nagoya.feature.result

import kuu.nagoya.feature.result.usecase.LoadRecordResultUsecase
import kuu.nagoya.feature.result.usecase.impl.LoadRecordResultUsecaseImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val resultModule = module {
    factory { LoadRecordResultUsecaseImpl(get()) as LoadRecordResultUsecase }

    // Viewmodel
    viewModel { ResultFragmentViewModel(get()) }
}