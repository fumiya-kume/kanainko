package jp.ac.daido.kanainko.record

internal interface RecordPresenter {
    fun requestAudioPermission(): Unit
    fun canUseAudiioPermission(): Boolean
}
