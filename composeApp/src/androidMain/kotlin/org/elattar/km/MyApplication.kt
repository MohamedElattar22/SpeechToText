package org.elattar.km

import android.app.Application
import org.elattar.km.di.initializeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidLogger()
            androidContext(this@MyApplication)
        }
    }
}