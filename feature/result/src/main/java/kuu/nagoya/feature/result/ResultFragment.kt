package kuu.nagoya.feature.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.util.Timer
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timerTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kuu.nagoya.feature.result.databinding.FragmentResultBinding
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
            .recordSpectrogramBitmapLivedata
            .observeForever { it ->
                binding.fragmentResultUserVoiceSpectrogramView.setImageBitmap(it)
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

        binding
            .fragmentResultPlayModelAudioButton
            .setOnClickListener {
                resultFragmentViewModel.playModelAudio()
            }

        return binding.root
    }
}
