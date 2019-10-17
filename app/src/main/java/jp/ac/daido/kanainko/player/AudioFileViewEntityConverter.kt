package jp.ac.daido.kanainko.player

import jp.ac.daido.kanainko.player.domain.model.AudioFileModel

internal fun AudioFileModel.convert(): AudioFileViewEntity =
    AudioFileViewEntity(this.id, this.fileName)
