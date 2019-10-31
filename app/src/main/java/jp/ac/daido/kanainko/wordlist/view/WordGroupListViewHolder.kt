package jp.ac.daido.kanainko.wordlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.daido.kanainko.databinding.ItemWordChooseGroupListBinding
import jp.ac.daido.kanainko.wordlist.viewentity.WordGroupViewEntity

internal class WordGroupListViewHolder private constructor(
    private val binding: ItemWordChooseGroupListBinding
) : RecyclerView.ViewHolder(
    binding.root
) {
    companion object {
        fun create(
            layoutInflator: LayoutInflater,
            container: ViewGroup
        ): WordGroupListViewHolder {
            return WordGroupListViewHolder(
                ItemWordChooseGroupListBinding.inflate(
                    layoutInflator,
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(
        viewEntity: WordGroupViewEntity,
        onWordGruopClickListener: OnWordGruopClickListener?
    ) {
        binding.viewentity = viewEntity

        binding.root.setOnClickListener {
            onWordGruopClickListener?.click(viewEntity)
        }
    }
}