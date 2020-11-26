package tech.thdev.kotlin_udemy_sample.view.main

import android.app.Application
import android.log.Log
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tech.thdev.kotlin_udemy_sample.view.koin.appModule

// 2. Android Application Class에서 startKoin()으로 실행하기 : MyApplication.class
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}