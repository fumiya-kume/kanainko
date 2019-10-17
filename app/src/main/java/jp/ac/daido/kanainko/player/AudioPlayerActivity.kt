package jp.ac.daido.kanainko.player

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.daido.kanainko.R
import kotlinx.android.synthetic.main.activity_audio_player.*

internal class AudioPlayerActivity : FragmentActivity() {

    lateinit var audioPlayerViewModel: AudioPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_audio_player)

        audioPlayerViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(AudioPlayerViewModel::class.java)

        audioPlayerViewModel.refreshAudioFileList()

        val audioFileAdapter = AudioFileAdapter(applicationContext)

        audioFileAdapter.onAudioFileClicled = object : OnAudioFileClicled {
            override fun onClicked(viewEntity: AudioFileViewEntity) {
                audioPlayerViewModel.playAudio(viewEntity)
            }
        }

        activity_audio_player_audio_list_recycler_view.layoutManager =
            LinearLayoutManager(applicationContext)
        activity_audio_player_audio_list_recycler_view.adapter = audioFileAdapter

        audioPlayerViewModel.audioFileListLiveData.observe(this, Observer {
            audioFileAdapter.submitList(it)
        })

        audioPlayerViewModel.audioPositionUpdateLiveData.observe(this, Observer {
            Toast.makeText(applicationContext, "Notify", Toast.LENGTH_SHORT).show()
        })
    }
}
