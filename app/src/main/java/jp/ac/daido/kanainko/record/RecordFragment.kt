package jp.ac.daido.kanainko.record

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import jp.ac.daido.kanainko.databinding.FragmentRecordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class RecordFragment : Fragment(), RecordPresenter {

    val recordViewModel: RecordViewModel by viewModel() { parametersOf(this) }

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
        val binding =
            FragmentRecordBinding.inflate(
                inflater,
                container,
                false
            )

        recordViewModel.volumeLiveData.observeForever {
        }

        recordViewModel.start()

        return binding.root
    }
}