package kuu.nagoya.dashboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kuu.nagoya.dashboard.viewentity.RecordViewEntity

internal class DashboardMusicListAdapter(
    private val context: Context
) :
    ListAdapter<RecordViewEntity, DashboardMusicListViewHolder>(
        DIFF_UTIL
    ) {

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<RecordViewEntity>() {
            override fun areItemsTheSame(
                oldItem: RecordViewEntity,
                newItem: RecordViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RecordViewEntity,
                newItem: RecordViewEntity
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
