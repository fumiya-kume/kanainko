package kuu.nagoya.dashboard.view.detaildialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kuu.nagoya.dashboard.databinding.FragmentRecordDetailBinding
import kuu.nagoya.dashboard.viewentity.RecordViewEntity

internal class RecordDetailDialog private constructor(
    private val recordViewEntity: RecordViewEntity
) : BottomSheetDialogFragment() {

    companion object {
        fun create(recordViewEntity: RecordViewEntity): RecordDetailDialog {
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

        binding.viewEntity = recordViewEntity.convert()

        return binding.root
    }
}