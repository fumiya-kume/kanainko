package jp.ac.daido.kanainko.graph.view.viewentity

import jp.ac.daido.kanainko.record.domain.model.FourierTransformationModel

internal fun FourierTransformationModel.convert(id: Int): FourierSoundDataViewEntity {
    return FourierSoundDataViewEntity(
        id,
        this.data
    )
}