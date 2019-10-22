package jp.ac.daido.kanainko.record.domain.model

internal fun List<Float>.convert(id: Int): SoundRawDataModel = SoundRawDataModel(
    id,
    this
)
