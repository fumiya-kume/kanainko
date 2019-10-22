package jp.ac.daido.kanainko.graph.domain.model

import jp.ac.daido.kanainko.record.domain.model.SoundVolumeModel

internal fun Float.convert(id: Int): SoundVolumeModel {
    return SoundVolumeModel(
        id,
        this
    )
}
