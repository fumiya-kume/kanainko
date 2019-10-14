package jp.ac.daido.kanainko.graph.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import jp.ac.daido.kanainko.CoroutinesTestRule
import jp.ac.daido.kanainko.graph.domain.model.SoundRawDataModel
import jp.ac.daido.kanainko.graph.domain.model.SoundVolumeModel
import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundRawDataUsecase
import jp.ac.daido.kanainko.graph.domain.usecase.LoadSoundVolumeUsecase
import jp.ac.daido.kanainko.lazyget
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.internal.verification.Times


internal class GraphViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun 生の音声情報を読み取るようにする() = coroutineTestRule.testDispatcher.runBlockingTest {
        val rawSoundDataChannel = Channel<SoundRawDataModel>(1)

        val loadRawSoundDataUsecase = Mockito.mock(LoadSoundRawDataUsecase::class.java)
            .apply {
                `when`(this.loadSoundRawData()).thenReturn(rawSoundDataChannel.consumeAsFlow())
            }
        val soundRawDataLiveDataFactory = SoundRawDataLiveDataFactory(loadRawSoundDataUsecase)

        val soundVolumeUsecaseFlow: Flow<SoundVolumeModel> = flowOf()
        val loadSoundVolumeUsecase = Mockito.mock(LoadSoundVolumeUsecase::class.java).apply {
            `when`(this.loadSoundVolume()).thenReturn(soundVolumeUsecaseFlow)
        }
        val soundVolumeLiveDataFactory = SoundVolumeLiveDataFactory(loadSoundVolumeUsecase)

        val subject = GraphViewModel(
            soundVolumeLiveDataFactory,
            soundRawDataLiveDataFactory
        )

        rawSoundDataChannel.send(
            SoundRawDataModel(
                0,
                listOf(
                    0.1F,
                    0.2F
                )
            )
        )

        subject.startRecording()

        val result = subject.soundRawLiveData.lazyget()
        assert(result.rawData.count() == 2)

        verify(loadRawSoundDataUsecase, Times(1)).loadSoundRawData()
        verify(loadSoundVolumeUsecase, Times(1)).loadSoundVolume()
    }

    @Test
    fun 音の大きさを読み取れるようにする() = coroutineTestRule.testDispatcher.runBlockingTest {
        val rawSoundDataChannel = Channel<SoundRawDataModel>(1)

        val loadRawSoundDataUsecase = Mockito.mock(LoadSoundRawDataUsecase::class.java)
            .apply {
                `when`(this.loadSoundRawData()).thenReturn(rawSoundDataChannel.consumeAsFlow())
            }
        val soundRawDataLiveDataFactory = SoundRawDataLiveDataFactory(loadRawSoundDataUsecase)

        val soundVolumeChannel = Channel<SoundVolumeModel>()
        val loadSoundVolumeUsecase = Mockito.mock(LoadSoundVolumeUsecase::class.java).apply {
            `when`(this.loadSoundVolume()).thenReturn(soundVolumeChannel.consumeAsFlow())
        }
        val soundVolumeLiveDataFactory = SoundVolumeLiveDataFactory(loadSoundVolumeUsecase)

        val subject = GraphViewModel(
            soundVolumeLiveDataFactory,
            soundRawDataLiveDataFactory
        )

        soundVolumeChannel.send(
            SoundVolumeModel(
                0,
                0.1F
            )
        )

        subject.startRecording()

        val result = subject.soundVolumeLiveData.lazyget()
        assert(result.volume == 0.1F)

        verify(loadRawSoundDataUsecase, Times(1)).loadSoundRawData()
        verify(loadSoundVolumeUsecase, Times(1)).loadSoundVolume()
    }
}