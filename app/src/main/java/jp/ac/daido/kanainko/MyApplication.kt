package jp.ac.daido.kanainko

import android.app.Application
import jp.ac.daido.kanainko.record.recordModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

internal class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val module = listOf<Module>(
            recordModule
        )

        startKoin {
            androidContext(this@MyApplication)
            modules(module)
        }
    }
}