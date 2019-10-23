package jp.ac.daido.kanainko.record.view.viewentity

import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel

internal fun SoundRawDataModel.convert(): RawSoundDataViewEntity {
    return RawSoundDataViewEntity(
        this.id,
        this.rawDara
    )
}