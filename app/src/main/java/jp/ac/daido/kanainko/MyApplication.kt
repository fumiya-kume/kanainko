package jp.ac.daido.kanainko

import android.app.Application
import jp.ac.daido.kanainko.record.recordModule
import jp.ac.daido.kanainko.wordlist.wordListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val module = recordModule + wordListModule

        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}
