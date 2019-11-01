package jp.ac.daido.kanainko.record.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import jp.ac.daido.kanainko.databinding.FragmentRecordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class RecordFragment : Fragment() {

    private val recordViewModel: RecordViewModel by viewModel { parametersOf(this) }
    private lateinit var binding: FragmentRecordBinding
    private val recordFragmentArgs: RecordFragmentArgs by navArgs()

    private val audioPermissionRequestCode: Int = 4223
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentRecordBinding.inflate(
                inflater,
                container,
                false
            )

        val wordViewEntity = recordFragmentArgs.recordWordEntity

        binding.fragmentRecordAudioVolumeTextView.text = "16000"

        binding
            .fragmentRecordRecordMaterialButton
            .setOnClickListener {
                Toast.makeText(requireContext(), "録音開始します", Toast.LENGTH_SHORT).show()
            }

        return binding.root
    }
}
