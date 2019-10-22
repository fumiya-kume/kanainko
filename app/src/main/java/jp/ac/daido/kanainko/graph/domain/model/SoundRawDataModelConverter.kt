package jp.ac.daido.kanainko.graph.domain.model

import jp.ac.daido.kanainko.record.domain.model.SoundRawDataModel

internal fun List<Float>.convert(id: Int): SoundRawDataModel = SoundRawDataModel(
    id,
    this
)
