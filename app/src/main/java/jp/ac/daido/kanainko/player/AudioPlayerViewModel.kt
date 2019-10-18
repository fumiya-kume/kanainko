package jp.ac.daido.kanainko.player

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.io.FileInputStream
import jp.ac.daido.kanainko.player.domain.AudioFileReadOnlyRepository
import jp.ac.daido.kanainko.player.domain.AudioFileReadOnlyRepositoryImpl
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class AudioPlayerViewModel(
    private val audioFileReadonlyRepository: AudioFileReadOnlyRepository = AudioFileReadOnlyRepositoryImpl()
) : ViewModel() {

    private val _audioFileListLiveData: MutableLiveData<List<AudioFileViewEntity>> =
        MutableLiveData()
    val audioFileListLiveData: LiveData<List<AudioFileViewEntity>> = _audioFileListLiveData

    private val _audioPositionUpdateLiveData: MutableLiveData<String> = MutableLiveData()
    val audioPositionUpdateLiveData: LiveData<String> = _audioPositionUpdateLiveData

    fun refreshAudioFileList() = viewModelScope.launch {
        val file =
            withContext(viewModelScope.coroutineContext) {
                audioFileReadonlyRepository
                    .loadAudioFileList()
                    .single()
            }

        _audioFileListLiveData.postValue(
            file?.mapIndexed { index, file -> AudioFileViewEntity(index, file.fileName) }
        )
    }

    fun playAudio(fileInfo: AudioFileViewEntity) = viewModelScope.launch {
        val audioId = fileInfo.id
        val file = withContext(viewModelScope.coroutineContext) {
            audioFileReadonlyRepository
                .loadAudioFileList()
                .single()
                ?.elementAt(audioId)
                ?.convert()
        }

        val samplingRate = 44100
        val audioAttributes = AudioAttributes
            .Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
        val audioFormat = AudioFormat
            .Builder()
            .setSampleRate(samplingRate)
            .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .build()

        val minBufer = AudioTrack.getMinBufferSize(
            samplingRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        val audioTrack = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioTrack
                .Builder()
                .setAudioAttributes(audioAttributes)
                .setAudioFormat(audioFormat)
                .setBufferSizeInBytes(minBufer)
                .build()
        } else {
            AudioTrack(audioAttributes, audioFormat, 256, 1, 1)
        }

        val audioByteArray = FileInputStream("sdcard/kanainko/${file?.fileName}").readBytes()

        audioTrack.setPositionNotificationPeriod(samplingRate)
        audioTrack.setPlaybackPositionUpdateListener(object :
            AudioTrack.OnPlaybackPositionUpdateListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onMarkerReached(p0: AudioTrack?) {
            }

            override fun onPeriodicNotification(p0: AudioTrack?) {
                _audioPositionUpdateLiveData.postValue(p0?.underrunCount.toString())
            }
        })

        do {
            val ret = audioTrack.write(audioByteArray, 44, audioByteArray.size - 44)
        } while (ret == audioByteArray.size)

        audioTrack.play()
    }
}
