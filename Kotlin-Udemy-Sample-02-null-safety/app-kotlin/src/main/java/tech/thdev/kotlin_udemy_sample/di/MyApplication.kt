package tech.thdev.kotlin_udemy_sample.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * 여기서 주의해야할 점은 Application Class를 만들었으면 AndroidManifest.xml에 android:name에 적용 해주어야 합니다.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}