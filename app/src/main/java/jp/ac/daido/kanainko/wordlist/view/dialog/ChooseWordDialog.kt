package jp.ac.daido.kanainko.wordlist.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.ac.daido.kanainko.databinding.DialogWordChooseBinding
import jp.ac.daido.kanainko.wordlist.viewentity.WordViewEntity

internal class ChooseWordDialog private constructor(
    private val wordList: List<WordViewEntity>
) : BottomSheetDialogFragment() {

    companion object {
        fun create(
            wordList: List<WordViewEntity>
        ): ChooseWordDialog {
            return ChooseWordDialog(wordList)
        }
    }

    var onWordChooseDoneListener: OnWordChooseDoneListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DialogWordChooseBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val chooseWordListAdapter = ChooseWordListAdapter(requireContext())
        chooseWordListAdapter.submitList(wordList)

        chooseWordListAdapter.onWordChooseListener = object : OnWordChooseListener {
            override fun choose(wordViewEntity: WordViewEntity) {
                chooseWordListAdapter.submitList(chooseWordListAdapter.currentList.map {
                    it.copy(
                        isChoose = wordViewEntity.id == it.id
                    )
                })
            }
        }

        chooseWordListAdapter.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                binding.dialogWordChooseEnterButton.isEnabled =
                    chooseWordListAdapter.currentList.any { it.isChoose }
            }
        })

        binding.dialogWordChooseEnterButton.setOnClickListener {
            onWordChooseDoneListener?.chooseDone(
                chooseWordListAdapter.currentList.firstOrNull { it.isChoose }
                    ?: return@setOnClickListener)
        }

        binding.dialogWordChooseWordListRecyclerView.adapter = chooseWordListAdapter

        return binding.root
    }
}