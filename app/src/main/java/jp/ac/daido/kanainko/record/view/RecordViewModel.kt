package jp.ac.daido.kanainko.record.view

import androidx.lifecycle.ViewModel
import jp.ac.daido.kanainko.record.domain.repository.AudioRepository

internal class RecordViewModel(
    private val audioRepository: AudioRepository
) : ViewModel()
