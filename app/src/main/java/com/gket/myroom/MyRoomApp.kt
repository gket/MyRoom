package com.gket.myroom

import android.app.Application
import com.gket.myroom.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyRoomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@MyRoomApp) }
        loadKoinModules(appModule)
    }
}