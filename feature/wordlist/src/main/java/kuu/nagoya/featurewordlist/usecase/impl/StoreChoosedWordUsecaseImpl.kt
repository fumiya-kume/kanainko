package kuu.nagoya.featurewordlist.usecase.impl

import kuu.nagoya.data.tmprecorddata.TmpRecordDataReadonlyRepository
import kuu.nagoya.data.tmprecorddata.TmpRecordDataRepository
import kuu.nagoya.featurewordlist.usecase.StoreChoosedWordUsecase

internal class StoreChoosedWordUsecaseImpl(
    private val tmpRecordDataRepository: TmpRecordDataRepository,
    private val tmpRecordDataReadonlyRepository: TmpRecordDataReadonlyRepository
) :
    StoreChoosedWordUsecase {
    override fun storeWord(word: Char) {

        tmpRecordDataRepository.clear()

        val tmpRecordData = tmpRecordDataReadonlyRepository.loadTmpRecordData()
        tmpRecordDataRepository.storeTmpData(
            tmpRecordData.copy(chooseCharacter = word)
        )
    }
}