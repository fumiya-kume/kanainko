package kuu.nagoya.featurewordlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.featurewordlist.databinding.ItemWordChooseGroupListBinding

import kuu.nagoya.featurewordlist.viewentity.WordGroupViewEntity

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