package jp.ac.daido.kanainko.record.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import jp.ac.daido.kanainko.databinding.FragmentRecordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class RecordFragment : Fragment() {

    private val recordViewModel: RecordViewModel by viewModel { parametersOf(this) }
    private lateinit var binding: FragmentRecordBinding

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
        val recorder = Recorder(requireContext())

        binding.fragmentRecordAudioVolumeAudioVolmeGraph.setMediaRecorder(recorder)

        binding
            .fragmentRecordRecordMaterialButton
            .setOnClickListener {
                if (recorder.recorderStatus == RecorderStatus.recording) {
                    recorder.stopRecording()
                } else {
                    recorder.startReocrding()
                }
            }

        recorder.onRecorderStatusUpdateListener = object : OnRecorderStatusUpdateListener {
            override fun onStatusUpdated(status: RecorderStatus) {
                when (status) {
                    is RecorderStatus.ready -> {
                        binding.fragmentRecordInitializationProgressBar.visibility = View.GONE
                    }
                    is RecorderStatus.recording -> {
                        binding.fragmentRecordRecordMaterialButton.text = "録音しています"
                    }
                    is RecorderStatus.stopping -> {
                        binding.fragmentRecordRecordMaterialButton.text = "録音する"
                    }
                    is RecorderStatus.error -> {
                        Toast.makeText(
                            requireContext(),
                            status.exception.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                    }
                }
            }
        }
        recorder.forceRecorderStusUpdate()
        return binding.root
    }
}