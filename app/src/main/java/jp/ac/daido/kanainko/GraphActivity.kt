package jp.ac.daido.kanainko

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

internal class GraphActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val chart = findViewById<LineChart>(R.id.main_chart)

        chart.run {
            val lineDataSet = LineData(
                listOf(
                    LineDataSet(
                        (1..2).map {
                            Entry(it.toFloat(), it.toFloat())
                        },
                        "Hello"
                    )
                )
            )

            this.data = lineDataSet
            this.invalidate()
        }
    }
}