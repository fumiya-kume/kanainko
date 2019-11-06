package kuu.nagoya.dashboard.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kuu.nagoya.dashboard.databinding.ItemDashboardMusicListBinding
import kuu.nagoya.dashboard.viewentity.RecordViewEntity

internal class DashboardMusicListViewHolder private constructor(
    private val binding: ItemDashboardMusicListBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            layoutInflater: LayoutInflater,
            container: ViewGroup
        ): DashboardMusicListViewHolder {
            return DashboardMusicListViewHolder(
                ItemDashboardMusicListBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(
        viewEntity: RecordViewEntity,
        onMusicListItemClickListener: OnMusicListItemClickListener?
    ) {
        binding.viewEntity = viewEntity
        binding.root.setOnClickListener {
            onMusicListItemClickListener?.clicked(viewEntity)
        }
    }
}