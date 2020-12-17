package com.rubenarenas.oompasnap.ui

import android.app.Application
import com.rubenarenas.oompasnap.data.api.dataModule
import org.koin.core.context.startKoin

class OompaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private val appComponents = listOf(
        appModule,
        dataModule
    )

    private fun startKoin() {
        startKoin {
            modules(appComponents)
        }
    }
}

