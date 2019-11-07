package kuu.nagoya.feature.record.domain.model

internal fun Float.convert(id: Int): SoundVolumeModel {
    return SoundVolumeModel(
        id,
        this
    )
}
