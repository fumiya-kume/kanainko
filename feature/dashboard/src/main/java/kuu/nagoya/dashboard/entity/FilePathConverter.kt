package kuu.nagoya.dashboard.entity

internal fun String.toFilePath(): FilePath {
    if (this.isEmpty()) {
        throw IllegalArgumentException("invalid file path")
    }
    return FilePath(this)
}