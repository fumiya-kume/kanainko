package jp.ac.daido.kanainko.graph.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import jp.ac.daido.kanainko.R
import kotlinx.android.synthetic.main.activity_graph.*
import org.koin.android.viewmodel.ext.android.viewModel

internal class GraphActivity : FragmentActivity() {

    private val graphViewModel: GraphViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        graphViewModel.soundVolumeLiveData.observeForever {
            val soundVolumeChart = sound_volume_bar_chart

            val barDataSet = BarData(
                listOf(
                    BarDataSet(
                        listOf(
                            BarEntry(
                                1F,
                                it.volume
                            )
                        ),
                        "音量"
                    )
                )
            )
            Log.d("tag", "get")

            soundVolumeChart.data = barDataSet
            soundVolumeChart.invalidate()
        }

        graphViewModel.soundRawLiveData.observeForever {
            val chart = main_chart
            val lineDataSet = LineData(
                listOf(
                    LineDataSet(
                        it.rawData.mapIndexed { index, item ->
                            Entry(index.toFloat(), item)
                        },
                        "生の音声データ"
                    )
                )
            )

            chart.data = lineDataSet
            chart.invalidate()
        }

        graphViewModel.startRecording()
    }
}