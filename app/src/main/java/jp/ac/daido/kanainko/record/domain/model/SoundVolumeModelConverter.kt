package jp.ac.daido.kanainko.record.domain.model

internal fun Float.convert(id: Int): SoundVolumeModel {
    return SoundVolumeModel(
        id,
        this
    )
}
