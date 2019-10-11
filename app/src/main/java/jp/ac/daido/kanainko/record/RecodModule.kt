package jp.ac.daido.kanainko.record

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recordModule = module {
    viewModel { (presenter: RecordPresenter) -> RecordViewModel(presenter) }

}