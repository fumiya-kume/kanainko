package kuu.nagoya.feature.record.legacy.domain.model

internal fun List<Float>.convert(id: Int): SoundRawDataModel =
    SoundRawDataModel(
        id,
        this
    )
