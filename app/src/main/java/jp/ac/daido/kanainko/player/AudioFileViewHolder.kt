package jp.ac.daido.kanainko.player

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.ac.daido.kanainko.databinding.ItemAudioPlayerAudioBinding

internal class AudioFileViewHolder(
    private val binding: ItemAudioPlayerAudioBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            context: Context,
            container: ViewGroup
        ): AudioFileViewHolder = AudioFileViewHolder(
            ItemAudioPlayerAudioBinding.inflate(
                LayoutInflater.from(context),
                container,
                false
            )
        )
    }

    fun bindTo(
        audioFileViewEntity: AudioFileViewEntity,
        onAudioFileClicled: OnAudioFileClicled?
    ) {
        binding.viewEntity = audioFileViewEntity
        binding.root.setOnClickListener {
            onAudioFileClicled?.onClicked(audioFileViewEntity)
        }
    }
}
