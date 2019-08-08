package jp.ac.daido.kanainko.record.domain.service

interface AudioRecordService {
    fun startRecord(): Unit
    fun stopRecord(): Unit
    val audioData: List<Short>
}