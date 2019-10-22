package jp.ac.daido.kanainko.graph.view.viewentity

import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel

internal fun SoundRawDataModel.convert(): SoundRawDataViewEntity {
    return SoundRawDataViewEntity(
        this.id,
        this.rawDara
    )
}
