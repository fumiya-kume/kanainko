package kuu.nagoya.feature.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.result.databinding.FragmentResultBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultFragment : Fragment() {

    private val resultFragmentViewModel: ResultFragmentViewModel by viewModel()
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding
            .inflate(
                layoutInflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultFragmentViewModel
            .userVoiceSpectrogramBitmap
            .observeForever { it ->
                binding
                    .fragmentResultUserVoiceSpectrogramImageView
                    .setImageBitmap(it)
            }

        resultFragmentViewModel
            .modelVoiceSpectrogramBitmap
            .observeForever {
                binding
                    .fragmentResultModelVoiceSpectrogramImageView
                    .setImageBitmap(it)
            }

        resultFragmentViewModel
            .userChooseWord
            .observeForever {
                binding
                    .fragmentResultUserPredictTextView.text = it.toString()
            }

        resultFragmentViewModel
            .predictWord
            .observeForever {
                binding
                    .fragmentResultChooseTextView.text = it
            }

        binding
            .fragmentResultGoBackButton
            .setOnClickListener {
                resultFragmentViewModel.goBackHomeScreen(this)
            }

        binding
            .fragmentResultPlayUserVoiceButton
            .setOnClickListener {
                resultFragmentViewModel.playUSerVoice()
            }

        binding
            .fragmentResultPlayModelVoiceButton
            .setOnClickListener {
                resultFragmentViewModel.playModelVoice()
            }
    }
}
