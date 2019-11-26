package kuu.nagoya.feature.record.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kuu.nagoya.feature.record.databinding.FragmentRecordBinding
import kuu.nagoya.feature.record.view.recorder.OnRecorderStatusUpdateListener
import kuu.nagoya.feature.record.view.recorder.Recorder
import kuu.nagoya.feature.record.view.recorder.RecorderStatus
import kuu.nagoya.navigation.RecordNavigation
import kuu.nagoya.util.showSnackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RecordFragment : Fragment() {

    private val recordViewModel: RecordViewModel by viewModel { parametersOf(this) }
    private lateinit var binding: FragmentRecordBinding
    private val recordNavigation: RecordNavigation by inject(parameters = { parametersOf(this) })

    private val recorder: Recorder by inject()

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


        binding.fragmentRecordAudioVolumeAudioVolmeGraph.setMediaRecorder(recorder)

        binding
            .fragmentRecordRecordMaterialButton
            .setOnClickListener {
                if (recorder.recorderStatus == RecorderStatus.recording) {
                    recorder.stop()
                    Log.d("audio", "record stopped")
                } else {
                    recorder.start()
                    Log.d("audio", "record started")
                }
            }

        recorder.onRecorderStatusUpdateListener = object :
            OnRecorderStatusUpdateListener {
            override fun onStatusUpdated(status: RecorderStatus) {
                when (status) {
                    is RecorderStatus.ready -> {
                        Log.d("record-event", "onReady")

                    }
                    is RecorderStatus.recording -> {
                        Log.d("record-event", "OnRecording")
                        binding.fragmentRecordInitializationProgressBar.visibility = View.GONE
                        binding.fragmentRecordRecordMaterialButton.text = "録音しています"
                    }
                    is RecorderStatus.stopping -> {
                        Log.d("record-event", "onStopping")
                        binding.fragmentRecordRecordMaterialButton.text = "録音する"
                        recordNavigation.navigateToAnalyzer()
                    }
                    is RecorderStatus.error -> {
                        Log.d("record-event", "onError")
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

        recordViewModel
            .tmpRecordLiveData
            .observeForever {
                when (it) {
                    is TmpRecordStatus.Created -> {
                        binding.viewentity = it.tmpRecord
                    }
                    is TmpRecordStatus.Restarted -> {
                        binding.viewentity = it.tmpRecord
                    }
                    TmpRecordStatus.Saved -> {
                        showSnackbar("保存に成功しました")
                    }
                    TmpRecordStatus.StartTmpRecordFailed -> {
                        showSnackbar("録音の開始に失敗しました")
                    }
                    TmpRecordStatus.RestartTmpRecordFailed -> {
                        showSnackbar("録音の初期化に失敗しました")
                    }
                    TmpRecordStatus.SavedTmpRecordFailed -> {
                        showSnackbar("録音データの保存に失敗しました")
                    }
                    else -> {
                        showSnackbar("illegal State exception()")
                    }
                }

            }

        recordViewModel
            .createTmpRecording()

        return binding.root
    }
}
