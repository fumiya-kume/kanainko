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

                val heightSize = 500
                val data =
                    audioData
                        .chunked(heightSize)
                        .map {
                            val data = it.map { it.toDouble() }.toDoubleArray()
                            val fft = DoubleFFT_1D(it.size)
                            fft.realForward(data)
                            data.filterIndexed { index, _ -> index % 2 == 0 }
                        }

                val width = (audioData.size / heightSize - 1)
                val height: Int = heightSize / 2
                val imageData = data.flatten().map { 200 - it }.map { it.toInt() }.toIntArray()
                val bitmap = Bitmap.createBitmap(imageData, width, height, Bitmap.Config.ARGB_8888)
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

        binding
            .fragmentResultPlayRecordAudioButton
            .setOnClickListener {
                resultFragmentViewModel.playRecordAudio()
            }

        return binding.root
    }
}
