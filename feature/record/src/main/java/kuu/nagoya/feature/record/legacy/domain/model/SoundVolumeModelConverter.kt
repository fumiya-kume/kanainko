package kuu.nagoya.feature.record.legacy.domain.model

internal fun Float.convert(id: Int): SoundVolumeModel {
    return SoundVolumeModel(
        id,
        this
    )
}
