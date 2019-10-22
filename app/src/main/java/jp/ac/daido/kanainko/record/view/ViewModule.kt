package jp.ac.daido.kanainko.record.view

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    factory { SoundRawDataLiveDataFactory(get()) }
    factory { SoundFourierDataLiveDataFactory(get(), get()) }
    viewModel { (recordPresenter: RecordPresenter) -> RecordViewModel(recordPresenter, get(), get()) }
}