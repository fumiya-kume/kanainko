package jp.ac.daido.kanainko

import android.app.Application
import jp.ac.daido.kanainko.record.recordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val module = listOf(
            recordModule
        )

        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}
