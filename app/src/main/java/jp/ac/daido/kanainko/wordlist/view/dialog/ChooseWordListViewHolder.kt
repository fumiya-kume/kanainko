package jp.ac.daido.kanainko.wordlist.view.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.daido.kanainko.databinding.ItemWordChooseDialogBinding
import jp.ac.daido.kanainko.wordlist.viewentity.WordViewEntity

internal class ChooseWordListViewHolder private constructor(
    private val binding: ItemWordChooseDialogBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            context: Context,
            parent: ViewGroup
        ): ChooseWordListViewHolder {
            return ChooseWordListViewHolder(
                ItemWordChooseDialogBinding.inflate(
                    LayoutInflater.from(context),
                    parent,
                    false
                )
            )
        }
    }

    fun bindTo(
        wordViewEntity: WordViewEntity,
        onWordChooseListener: OnWordChooseListener?
    ) {
        binding.viewentity = wordViewEntity
        binding.root.setOnClickListener {
            onWordChooseListener?.choose(wordViewEntity)
        }
    }
}