package jp.ac.daido.kanainko.graph.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.*
import jp.ac.daido.kanainko.R
import kotlinx.android.synthetic.main.activity_graph.*

internal class GraphActivity : FragmentActivity() {

    private val graphViewModel = ViewModelProvider.NewInstanceFactory().create(GraphViewModel::class.java)

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


    }
}