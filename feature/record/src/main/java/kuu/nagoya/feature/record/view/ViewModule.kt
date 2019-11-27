package kuu.nagoya.feature.record.view

import kuu.nagoya.feature.record.usecase.LoadRecordFilePathUsecase
import kuu.nagoya.feature.record.usecase.impl.LoadRecordFilePathUsecaseImpl
import kuu.nagoya.feature.record.view.recorder.Recorder
import kuu.nagoya.feature.record.view.recorder.RecorderImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { LoadRecordFilePathUsecaseImpl(get(), get(), get()) as LoadRecordFilePathUsecase }

    factory { RecorderImpl(androidApplication()) as Recorder }

    factory { TmpRecordLiveDataFactory(get()) }
    viewModel { RecordViewModel(get()) }
}
