package jp.ac.daido.kanainko.record.domain.service

internal class AudioRecordServiceImpl() : AudioRecordService {

    init {

    }

    override val audioData: List<Short> = emptyList()

    override fun startRecord() {

    }

    override fun stopRecord() {

    }

//    override var audioData: List<Short> = emptyList()
//
//    private var audioRecord: AudioRecord
//
//    private val samplingRate: Int = 44100
//    private val channelConfig: Int = 1
//    private val oneFrameDataSize = samplingRate / 2 * 50
//    private val frameBufferSize = max(
//        oneFrameDataSize,
//        AudioRecord.getMinBufferSize(
//            samplingRate,
//            channelConfig,
//            AudioFormat.ENCODING_PCM_8BIT
//        )
//    )
//
//    init {
//
//
//
//        audioRecord = AudioRecord(
//            MediaRecorder.AudioSource.MIC,
//            samplingRate,
//            channelConfig,
//            AudioFormat.ENCODING_PCM_8BIT,
//            frameBufferSize
//        )
//    }
//
//    override fun startRecord() {
//        audioRecord.startRecording()
//
//
//        audioRecord.positionNotificationPeriod = frameBufferSize
//
//        val bufferList = ShortArray(frameBufferSize)
//        audioRecord.setRecordPositionUpdateListener(object :
//            AudioRecord.OnRecordPositionUpdateListener {
//            override fun onMarkerReached(record: AudioRecord?) {
//                record?.read(bufferList, 0, frameBufferSize)
//                (audioData as MutableList<Short>).addAll(bufferList.toList())
//                Log.d("audio_record", bufferList.count().toString())
//            }
//
//            override fun onPeriodicNotification(p0: AudioRecord?) {
//                Log.d("audio_record", "marker attached")
//            }
//        })
//    }
//
//    override fun stopRecord() {
//        audioRecord.stop()
//        audioRecord.release()
//    }
}