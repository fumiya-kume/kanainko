package jp.ac.daido.kanainko.wordlist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import jp.ac.daido.kanainko.wordlist.viewentity.WordGroupViewEntity

internal class WordGroupListAdapter(
    private val context: Context
) : androidx.recyclerview.widget.ListAdapter<WordGroupViewEntity, WordGroupListViewHolder>(
    Diff_Util
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordGroupListViewHolder {
        return WordGroupListViewHolder.create(
            LayoutInflater.from(context),
            parent
        )
    }

    override fun onBindViewHolder(holder: WordGroupListViewHolder, position: Int) {
        return holder.bindTo(
            getItem(position),
            onWordGruopClickListener
        )
    }

    var onWordGruopClickListener: OnWordGruopClickListener? = null

    companion object {
        private val Diff_Util = object : DiffUtil.ItemCallback<WordGroupViewEntity>() {
            override fun areItemsTheSame(
                oldItem: WordGroupViewEntity,
                newItem: WordGroupViewEntity
            ): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(
                oldItem: WordGroupViewEntity,
                newItem: WordGroupViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}