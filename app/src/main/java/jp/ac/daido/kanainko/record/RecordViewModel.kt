package jp.ac.daido.kanainko.record

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.ac.daido.kanainko.record.domain.RecordFileNameRepository
import jp.ac.daido.kanainko.record.domain.service.AudioRecordService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext

internal class RecordViewModel(
    private val recordPresenter: RecordPresenter,
    private val recorddFileNameRepository: RecordFileNameRepository,
    private val audioRecordService: AudioRecordService
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private val _isRecordingLivedata: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRecordingLivedata: LiveData<Boolean> = _isRecordingLivedata

    val recordSoundDoneEvent: Channel<Boolean> = Channel()

    fun changeRecordState() {

        if (!recordPresenter.canUseAudiioPermission()) {
            recordPresenter.requestAudioPermission()
            return
        }

        val recordStateSnapshot = _isRecordingLivedata.value ?: false
        if (recordStateSnapshot) {
            audioRecordService.stopRecord()
            _isRecordingLivedata.postValue(false)
            Log.d("record_view_model", audioRecordService.audioData.count().toString())
        } else {
            audioRecordService.startRecord()
            _isRecordingLivedata.postValue(true)
        }
    }
}