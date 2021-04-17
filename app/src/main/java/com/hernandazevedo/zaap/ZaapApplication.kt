package com.hernandazevedo.zaap

import android.app.Application
import com.hernandazevedo.zaap.di.zaapModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZaapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDi()
    }

    private fun initializeDi() {
        startKoin {
            androidContext(this@ZaapApplication)
            modules(zaapModules)
        }
    }
}