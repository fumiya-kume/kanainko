package kuu.nagoya.entity.recordentity

fun String.toFilePath(): FilePath {
    if (this.isEmpty()) {
        throw IllegalArgumentException("invalid file path")
    }
    return FilePath(this)
}