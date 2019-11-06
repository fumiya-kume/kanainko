package jp.ac.daido.kanainko

import android.app.Application
import jp.ac.daido.kanainko.navigation.navigationModule
import jp.ac.daido.kanainko.wordlist.wordListModule
import kuu.nagoya.dashboard.dashBoardModule
import kuu.nagoya.data.recorddata.recordDataModule
import kuu.nagoya.feature.record.recordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val module =
            recordModule + wordListModule + navigationModule + dashBoardModule + recordDataModule

        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}
