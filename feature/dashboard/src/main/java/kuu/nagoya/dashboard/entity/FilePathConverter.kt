package kuu.nagoya.dashboard.entity

fun String.toFilePath(): kuu.nagoya.dashboard.entity.FilePath {
    if (this.isEmpty()) {
        throw IllegalArgumentException("invalid file path")
    }
    return kuu.nagoya.dashboard.entity.FilePath(this)
}