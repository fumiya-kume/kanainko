package jp.ac.daido.kanainko

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.media.audiofx.Visualizer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class GraphActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val chart = findViewById<LineChart>(R.id.main_chart)

        fun updateGraph(list: List<Float>) {
            val lineDataSet = LineData(
                listOf(
                    LineDataSet(
                        list.mapIndexed { index, item ->
                            Entry(index.toFloat(), item)
                        },
                        "Hello"
                    )
                )
            )

            chart.data = lineDataSet
            chart.invalidate()
        }


        val audioRecord: AudioRecord

        val samplingRate: Int = 44100
        val channelConfig: Int = 1
        val oneFrameDataSize = samplingRate / 2 * 50
        val frameBufferSize = Math.max(
            oneFrameDataSize,
            AudioRecord.getMinBufferSize(
                samplingRate,
                channelConfig,
                AudioFormat.ENCODING_PCM_8BIT
            )
        ) / 50

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            samplingRate,
            channelConfig,
            AudioFormat.ENCODING_PCM_8BIT,
            frameBufferSize
        )


        val period = frameBufferSize
        audioRecord.positionNotificationPeriod = period

        val bufferList = ShortArray(frameBufferSize)
        audioRecord.setRecordPositionUpdateListener(object :
            AudioRecord.OnRecordPositionUpdateListener {
            override fun onMarkerReached(record: AudioRecord?) {
                Log.d("audio_record", "maker attached")
            }

            override fun onPeriodicNotification(record: AudioRecord?) {
                Log.d("audio_record", "period attached")

                record?.read(bufferList, 0, frameBufferSize)
//                GlobalScope.launch { updateGraph(. map { it.toFloat() }) }

            }
        })

        audioRecord.startRecording()

        val visualizer = Visualizer(audioRecord.audioSessionId)
        val captureSize = Visualizer.getCaptureSizeRange()[1]
        visualizer.setCaptureSize(captureSize)


        visualizer.setDataCaptureListener(
            object : Visualizer.OnDataCaptureListener {
                override fun onFftDataCapture(
                    visualizer: Visualizer?,
                    fft: ByteArray?,
                    samplingRate: Int
                ) {
                    updateGraph(fft?.map { it.toFloat() } ?: emptyList())
                }

                // Wave のデータ
                override fun onWaveFormDataCapture(
                    visualizer: Visualizer?,
                    waveform: ByteArray?,
                    samplingRate: Int
                ) {

                }

            },
            captureSize,
            true,
            true
        )


    }
}