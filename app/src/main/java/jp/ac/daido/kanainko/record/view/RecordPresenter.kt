package jp.ac.daido.kanainko.record.view

internal interface RecordPresenter {
    fun requestAudioPermission(): Unit
    fun canUseAudiioPermission(): Boolean
}
