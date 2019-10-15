package jp.ac.daido.kanainko.graph.domain.model

internal fun List<Float>.convert(id: Int): SoundRawDataModel = SoundRawDataModel(
    id,
    this
)