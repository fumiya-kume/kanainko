package jp.ac.daido.kanainko.wordlist

import jp.ac.daido.kanainko.wordlist.infra.WordListReadonlyRepositoryImpl
import jp.ac.daido.kanainko.wordlist.usecase.WordListReadonlyRepository
import jp.ac.daido.kanainko.wordlist.view.ChooseWordViewModel
import jp.ac.daido.kanainko.wordlist.view.WordListLiveDataFactory
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wordListModule =
    module {
        factory { WordListReadonlyRepositoryImpl() as WordListReadonlyRepository }
        factory { WordListLiveDataFactory(get()) }
        viewModel { ChooseWordViewModel(get()) }
    }
