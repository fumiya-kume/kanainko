package jp.ac.daido.kanainko.player

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter

internal class AudioFileAdapter(
    private val context: Context
    ) :
    ListAdapter<AudioFileViewEntity, AudioFileViewHolder>(object :
        ItemCallback<AudioFileViewEntity>() {
        override fun areItemsTheSame(
            oldItem: AudioFileViewEntity,
            newItem: AudioFileViewEntity
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: AudioFileViewEntity,
            newItem: AudioFileViewEntity
        ) = oldItem.id == newItem.id
    }) {

    var onAudioFileClicled: OnAudioFileClicled? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioFileViewHolder =
        AudioFileViewHolder.create(context, parent)

    override fun onBindViewHolder(holder: AudioFileViewHolder, position: Int) {
        holder.bindTo(
            getItem(position),
            onAudioFileClicled
        )
    }
}