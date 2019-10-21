package jp.ac.daido.kanainko.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.daido.kanainko.databinding.ItemDashboardMusicListBinding

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

    fun bindTo(viewEntity: MusicListViewEntity) {
        binding.viewEntity = viewEntity
    }
}