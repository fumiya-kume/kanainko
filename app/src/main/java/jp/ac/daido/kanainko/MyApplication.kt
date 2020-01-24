package jp.ac.daido.kanainko

import android.app.Application
import com.prevent.voice_data.voiceDataModule
import jp.ac.daido.kanainko.navigation.navigationModule
import kuu.nagoya.dashboard.dashBoardModule
import kuu.nagoya.data.recorddata.recordDataModule
import kuu.nagoya.data.tmprecorddata.tmpRecordDataModule
import kuu.nagoya.feature.record.recordModule
import kuu.nagoya.feature.result.resultModule
import kuu.nagoya.featurewordlist.wordListModule
import nagoya.kuu.learning_data.learningDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val module =
            recordModule + wordListModule + navigationModule + dashBoardModule + recordDataModule + tmpRecordDataModule + resultModule + voiceDataModule + learningDataModule

        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}
