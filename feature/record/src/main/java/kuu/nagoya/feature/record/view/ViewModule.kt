package kuu.nagoya.feature.record.view

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { RecordViewModel(get()) }
}