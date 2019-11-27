package kuu.nagoya.featurewordlist

import kuu.nagoya.featurewordlist.infra.WordListReadonlyRepositoryImpl
import kuu.nagoya.featurewordlist.usecase.StoreChoosedWordUsecase
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository
import kuu.nagoya.featurewordlist.usecase.impl.StoreChoosedWordUsecaseImpl
import kuu.nagoya.featurewordlist.view.ChooseWordViewModel
import kuu.nagoya.featurewordlist.view.WordListLiveDataFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordListModule =
    module {

        // Repository
        factory { WordListReadonlyRepositoryImpl() as WordListReadonlyRepository }

        // Usecase
        factory { StoreChoosedWordUsecaseImpl(get(), get()) as StoreChoosedWordUsecase }

        factory { WordListLiveDataFactory(get()) }
        viewModel { ChooseWordViewModel(get(), get()) }
    }
