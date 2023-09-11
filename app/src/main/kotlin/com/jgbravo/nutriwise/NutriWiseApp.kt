package com.jgbravo.nutriwise

import android.app.Application
import com.jgbravo.logger.Logger
import com.jgbravo.nutriwise.di.appModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NutriWiseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NutriWiseApp)
            modules(appModule)
        }

        Logger.init(BuildConfig.DEBUG)
    }
}