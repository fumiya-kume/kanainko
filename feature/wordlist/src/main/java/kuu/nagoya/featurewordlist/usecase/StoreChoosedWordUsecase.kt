package kuu.nagoya.featurewordlist.usecase

import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

internal interface StoreChoosedWordUsecase {
    fun storeWord(wordViewEntity: WordViewEntity)
}
