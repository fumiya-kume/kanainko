package kuu.nagoya.featurewordlist

import kuu.nagoya.featurewordlist.infra.WordListReadonlyRepositoryImpl
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository
import kuu.nagoya.featurewordlist.view.ChooseWordViewModel
import kuu.nagoya.featurewordlist.view.WordListLiveDataFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordListModule =
    module {
        factory { WordListReadonlyRepositoryImpl() as WordListReadonlyRepository }
        factory { WordListLiveDataFactory(get()) }
        viewModel { ChooseWordViewModel(get()) }
    }
