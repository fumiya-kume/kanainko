package kuu.nagoya.feature.result

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D
import java.io.File
import java.util.Timer
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.result.databinding.FragmentResultBinding
import kuu.nagoya.waveparser.WaveParse
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : Fragment() {

    private val resultFragmentViewModel: ResultFragmentViewModel by viewModel()
    private val resultNavigation: ResultNavigation by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentResultBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        val timer = Timer()
        timer.schedule(timerTask {
            GlobalScope.launch(Dispatchers.Main) {
                binding.fragmentResultTensordlowMessageTextView.visibility = View.VISIBLE
            }
        }, TimeUnit.SECONDS.toMillis(1))
        timer.schedule(timerTask {
            GlobalScope.launch(Dispatchers.Main) {
                binding.fragmentResultProcessingAudioMessageTextView.visibility = View.VISIBLE
            }
        }, TimeUnit.SECONDS.toMillis(2))
        timer.schedule(
            timerTask {
                GlobalScope.launch(Dispatchers.Main) {
                    binding.fragmentResultLoadingView.visibility = View.GONE
                }
            },
            TimeUnit.SECONDS.toMillis(3)
        )

        resultFragmentViewModel
            .audioFilePathLiveData
            .observeForever { it ->
                val audioData = WaveParse.loadWaveFromFile(File(it)).data

                val data =
                    audioData
                        .chunked(500)
                        .map {
                            val data = DoubleArray(it.size)
                            val fft = DoubleFFT_1D(it.size)
                            fft.realForward(data)
                            data

//                            data.filterIndexed { index, d -> index % 2 == 0 }
                        }

                val width = data.size
                val height: Int = data[0].size
                val arrayCol = IntArray(width * height)
                var counter = 0
                (0 until height).forEach { i ->
                    (0 until width).forEach { j ->
                        if (data.size > j && data.elementAt(j).size > i) {
                            val value = 255 - (data.elementAt(j).elementAt(i) * 255).toInt()
                            val color = value
                            arrayCol[counter] = color
                            counter++
                        }
                    }
                }
                val bitmap = Bitmap.createBitmap(arrayCol, width, height, Bitmap.Config.ARGB_8888)
                binding.fragmentResultUserVoiceSpectrogramView.setImageBitmap(bitmap)
            }

        resultFragmentViewModel
            .chooseWordLiveData
            .observeForever {
                binding.fragmentResultPredictTextView.text = it.toString()
            }

        resultFragmentViewModel.load()

        binding
            .fragmentResultNavigateHomeButton
            .setOnClickListener {
                resultNavigation.navigateToHome(this)
            }

        binding
            .fragmentResultLearningMoreButton
            .setOnClickListener {
                resultNavigation.navigateWordChooseScreen(this)
            }

        return binding.root
    }
}
