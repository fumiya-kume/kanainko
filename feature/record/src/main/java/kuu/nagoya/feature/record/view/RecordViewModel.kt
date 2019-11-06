package kuu.nagoya.feature.record.view

import androidx.lifecycle.ViewModel
import kuu.nagoya.feature.record.domain.repository.AudioRepository

internal class RecordViewModel(
    private val audioRepository: AudioRepository
) : ViewModel()
