package kuu.nagoya.featurewordlist.view.dialog

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kuu.nagoya.featurewordlist.viewentity.WordViewEntity

internal class ChooseWordListAdapter(
    private val context: Context
) : ListAdapter<WordViewEntity, ChooseWordListViewHolder>(
    diff_util
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseWordListViewHolder {
        return ChooseWordListViewHolder.create(
            context,
            parent
        )
    }

    override fun onBindViewHolder(holder: ChooseWordListViewHolder, position: Int) {
        return holder.bindTo(
            getItem(position),
            onWordChooseListener
        )
    }

    var onWordChooseListener: OnWordChooseListener? = null

    companion object {
        private val diff_util = object : DiffUtil.ItemCallback<WordViewEntity>() {
            override fun areItemsTheSame(
                oldItem: WordViewEntity,
                newItem: WordViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: WordViewEntity,
                newItem: WordViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}