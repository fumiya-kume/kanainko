package jp.ac.daido.kanainko.graph.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import jp.ac.daido.kanainko.CoroutinesTestRule
import org.junit.Rule
import org.junit.rules.TestRule

internal class GraphViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutinesTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

//    @Test
//    fun 生の音声情報を読み取るようにする() = coroutineTestRule.testDispatcher.runBlockingTest {
//        val rawSoundDataChannel = Channel<SoundRawDataModel>(1)
//
//        val loadRawSoundDataUsecase = Mockito.mock(LoadSoundRawDataUsecase::class.java)
//            .apply {
//                `when`(this.execute()).thenReturn(rawSoundDataChannel.consumeAsFlow())
//            }
//        val soundRawDataLiveDataFactory = SoundRawDataLiveDataFactory(loadRawSoundDataUsecase)
//
//        val soundVolumeUsecaseFlow: Flow<SoundVolumeModel> = flowOf()
//        val loadSoundVolumeUsecase = Mockito.mock(LoadSoundVolumeUsecase::class.java).apply {
//            `when`(this.execute()).thenReturn(soundVolumeUsecaseFlow)
//        }
//        val soundVolumeLiveDataFactory = SoundVolumeLiveDataFactory(loadSoundVolumeUsecase)
//
//        val startAudioRecordingUsecase = mock(StartAudioRecordingUsecase::class.java).apply {
//            `when`(this.execute()).thenReturn(Unit)
//        }
//
//        val subject = GraphViewModel(
//            soundVolumeLiveDataFactory,
//            soundRawDataLiveDataFactory,
//            startAudioRecordingUsecase
//        )
//
//        rawSoundDataChannel.send(
//            SoundRawDataModel(
//                0,
//                listOf(
//                    0.1F,
//                    0.2F
//                )
//            )
//        )
//
//        subject.startRecording()
//
//        val result = subject.soundRawLiveData.lazyget()
//        assert(result.rawData.count() == 2)
//
//        verify(loadRawSoundDataUsecase, Times(1)).execute()
//        verify(loadSoundVolumeUsecase, Times(1)).execute()
//        verify(startAudioRecordingUsecase, Times(1)).execute()
//    }
//
//    @Test
//    fun 音の大きさを読み取れるようにする() = coroutineTestRule.testDispatcher.runBlockingTest {
//        val rawSoundDataChannel = Channel<SoundRawDataModel>(1)
//
//        val loadRawSoundDataUsecase = Mockito.mock(LoadSoundRawDataUsecase::class.java)
//            .apply {
//                `when`(this.execute()).thenReturn(rawSoundDataChannel.consumeAsFlow())
//            }
//        val soundRawDataLiveDataFactory = SoundRawDataLiveDataFactory(loadRawSoundDataUsecase)
//
//        val soundVolumeChannel: Channel<SoundVolumeModel> = Channel(1)
//        val loadSoundVolumeUsecase = Mockito.mock(LoadSoundVolumeUsecase::class.java).apply {
//            `when`(this.execute()).thenReturn(soundVolumeChannel.consumeAsFlow())
//        }
//        val soundVolumeLiveDataFactory = SoundVolumeLiveDataFactory(loadSoundVolumeUsecase)
//
//        val startAudioRecordingUsecase = mock(StartAudioRecordingUsecase::class.java).apply {
//            `when`(this.execute()).thenReturn(Unit)
//        }
//
//        val subject = GraphViewModel(
//            soundVolumeLiveDataFactory,
//            soundRawDataLiveDataFactory,
//            startAudioRecordingUsecase
//        )
//
//        soundVolumeChannel.send(
//            SoundVolumeModel(
//                0,
//                0.1F
//            )
//        )
//
//        subject.startRecording()
//
//        val result = subject.soundVolumeLiveData.lazyget()
//        assert(result.volume == 0.1F)
//
//        verify(loadRawSoundDataUsecase, Times(1)).execute()
//        verify(loadSoundVolumeUsecase, Times(1)).execute()
//        verify(startAudioRecordingUsecase, Times(1)).execute()
//    }
}
