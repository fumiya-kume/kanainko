package kuu.nagoya.feature.record.view.recorder

internal interface Recorder {
    var onRecorderStatusUpdateListener: OnRecorderStatusUpdateListener?
    val recorderStatus: RecorderStatus
    fun start()
    fun stop()
    fun getMaxAmplitude(): Double
    var outputFilePath: String
}
