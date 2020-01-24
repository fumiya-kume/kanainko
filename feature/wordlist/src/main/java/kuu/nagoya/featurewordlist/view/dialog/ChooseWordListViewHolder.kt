package kuu.nagoya.featurewordlist.view.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.featurewordlist.R
import kuu.nagoya.featurewordlist.databinding.ItemWordChooseDialogBinding
import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

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
        context: Context,
        onWordChooseListener: OnWordChooseListener?
    ) {
        binding.viewentity = wordViewEntity
        binding.root.setOnClickListener {
            onWordChooseListener?.choose(wordViewEntity)
        }
        binding
            .itemWordChooseDialogCheckBox
            .setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    onWordChooseListener?.choose(wordViewEntity)
                }
            }

        binding.learninedCheckIconImageView.imageTintList = if (wordViewEntity.isLearned) {
            ContextCompat.getColorStateList(context, R.color.secondaryColor)
        } else {
            ContextCompat.getColorStateList(context, R.color.grayColor)
        }
    }
}
