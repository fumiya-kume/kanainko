package kuu.nagoya.feature.record

internal data class AudioVolumeViewEntity(
    val id: Int,
    val volume: Double
) {
    companion object {
        fun of(id: Int, data: List<Float>): AudioVolumeViewEntity {
            return AudioVolumeViewEntity(
                id,
                data.map { Math.abs(it) }.max()?.toDouble() ?: 10.0
            )
        }
    }
}