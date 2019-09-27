package jp.ac.daido.kanainko

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import jp.ac.daido.kanainko.databinding.ActivityGraphBinding

internal class GraphActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

//        val binding: ActivityGraphBinding =
//            DataBindingUtil.bind(findViewById<ViewGroup>(android.R.id.content)[0])!!

//        val binding = DataBindingUtil.setContentView<ActivityGraphBinding>(this, R.layout.activity_graph)

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