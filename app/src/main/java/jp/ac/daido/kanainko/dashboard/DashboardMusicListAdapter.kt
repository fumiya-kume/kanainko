package jp.ac.daido.kanainko.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class DashboardMusicListAdapter(
    private val context: Context
) :
    ListAdapter<MusicListViewEntity, DashboardMusicListViewHolder>(
        DIFF_UTIL
    ) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<MusicListViewEntity>() {
            override fun areItemsTheSame(
                oldItem: MusicListViewEntity,
                newItem: MusicListViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MusicListViewEntity,
                newItem: MusicListViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardMusicListViewHolder {
        return DashboardMusicListViewHolder.create(
            LayoutInflater.from(context),
            parent
        )
    }

    override fun onBindViewHolder(holder: DashboardMusicListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
