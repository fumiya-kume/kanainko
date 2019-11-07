package kuu.nagoya.feature.record.domain.model

internal fun List<Float>.convert(id: Int): SoundRawDataModel =
    SoundRawDataModel(
        id,
        this
    )
