package jp.ac.daido.kanainko.record.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.ac.daido.kanainko.R
import jp.ac.daido.kanainko.databinding.FragmentRecordBinding
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class RecordFragment : Fragment(), RecordPresenter {

    private val recordViewModel: RecordViewModel by viewModel { parametersOf(this) }
    private lateinit var binding: FragmentRecordBinding
    private val audioRepository: AudioRepository by inject()

    private val audioPermissionRequestCode: Int = 4223

    override fun requestAudioPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            listOf(Manifest.permission.RECORD_AUDIO).toTypedArray(),
            audioPermissionRequestCode
        )
    }

    override fun canUseAudiioPermission() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

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

        binding.fragmentRecordFisnishRecordButton.setOnClickListener {
            findNavController().navigate(R.id.action_recordFragment_to_resultFragment)
        }

        recordViewModel
            .soundFrourierTransformLiveData
            .observeForever {
                GlobalScope.launch(Dispatchers.IO) {
                    val displayData = mutableListOf<Float>()
                    val displayElementCount = 50
                    val oneWindowCount = it.size / displayElementCount

                    for (i in 0..displayElementCount) {
                        if ((i + 1) * oneWindowCount > it.size) {
                            break
                        }
                        displayData.add(
                            it.subList(
                                i * oneWindowCount,
                                (i + 1) * oneWindowCount
                            ).average().toFloat()
                        )
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        binding.fragmentRecordFourierGraphLineChart.setData(displayData)
                    }
                }
            }

//        binding.fragmentRecordFourierGraphLineChart.setData(listOf(1.0F, 100.0F, 300.0F))

        return binding.root
    }
}
