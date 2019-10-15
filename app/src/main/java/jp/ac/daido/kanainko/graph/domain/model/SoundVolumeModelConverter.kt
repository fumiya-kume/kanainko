package jp.ac.daido.kanainko.graph.domain.model

internal fun Float.convert(id: Int): SoundVolumeModel{
    return SoundVolumeModel(
        id,
        this
    )
}