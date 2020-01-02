package kuu.nagoya.dashboard.view.detaildialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kuu.nagoya.dashboard.databinding.FragmentRecordDetailBinding
import kuu.nagoya.dashboard.usecase.PlayAudioUsecase
import kuu.nagoya.dashboard.viewentity.RecordViewEntity
import kuu.nagoya.dashboard.viewentity.convertBack
import org.koin.android.ext.android.inject

internal class RecordDetailDialog private constructor(
    private val recordViewEntity: RecordViewEntity
) : BottomSheetDialogFragment() {

    private val playAudioUsecase: PlayAudioUsecase by inject()

    companion object {
        fun create(
            recordViewEntity: RecordViewEntity
        ): RecordDetailDialog {
            return RecordDetailDialog(recordViewEntity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecordDetailBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        binding.fragmentRecordDetailPlayRecordMusicButton.setOnClickListener {

            GlobalScope.launch {
                playAudioUsecase.execute(recordViewEntity.convertBack())
            }
        }
        binding.viewEntity = recordViewEntity.convert()

        return binding.root
    }
}
