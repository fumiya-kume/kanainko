package jp.ac.daido.kanainko.graph.view.viewentity

import jp.ac.daido.kanainko.graph.domain.model.SoundVolumeModel

internal fun SoundVolumeModel.convert(): SoundVolumeViewEntity {
    return SoundVolumeViewEntity(
        this.id,
        this.volume
    )
}
