package kuu.nagoya.feature.result

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D
import kuu.nagoya.feature.result.databinding.FragmentResultBinding
import kuu.nagoya.waveparser.WaveParse
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class ResultFragment : Fragment() {

    private val resultFragmentViewModel: ResultFragmentViewModel by viewModel()

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

        resultFragmentViewModel
            .audioFilePathLiveData
            .observeForever { it ->
                val audioData = WaveParse.loadWaveFromFile(File(it)).data.map { it.toDouble() }

//                val heightSize = audioData.size / 1000
//                val width = 1000
//                val sourceData = mutableListOf<List<Double>>()
//                (0 until width - 1).forEach {
//                    sourceData.add(audioData.take(heightSize))
//                }
                val chunkedAudioData =
                    audioData.chunked(500).map { it.toDoubleArray() }
                        .toTypedArray()

//
//                binding.fragmentResultUserVoiceSpectrogramView.setData(
//                    sourceData
//                        .map { data ->
//                            val fft = DoubleFFT_1D(data.size - 1)
//                            fft.realForwardFull(data.toDoubleArray())
//                            return@map data.toList()
//                        }
//                )

                val power = chunkedAudioData
                    .map { data ->
                        val fft = DoubleFFT_1D(data.size)
                        fft.realForward(data)

                        return@map (0..data.size - 2)
                            .filter { it % 2 != 2 }
                            .map {
                                Math.sqrt(
                                    Math.pow(
                                        data.elementAt(it),
                                        2.0
                                    ) + Math.pow(data.elementAt(it + 1), 2.0)
                                )
                            }
                            .map { it / data.size }
                    }

//                binding.fragmentResultUserVoiceSpectrogramView.setData(chunkedAudioData.map { it.toList() }.toList())
                val data = chunkedAudioData
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

        return binding.root
    }
}