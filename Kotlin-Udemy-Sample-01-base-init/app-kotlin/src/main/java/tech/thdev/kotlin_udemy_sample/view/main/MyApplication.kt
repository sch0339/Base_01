package tech.thdev.kotlin_udemy_sample.view.main

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tech.thdev.kotlin_udemy_sample.BuildConfig
import tech.thdev.kotlin_udemy_sample.view.koin.appModule

// 2. Android Application Class에서 startKoin()으로 실행하기 : MyApplication.class
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }

        // Logger.addLogAdapter(AndroidLogAdapter()) // 기본.
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true) // 쓰레드보여줄 여부
            .tag("LOGGER")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}