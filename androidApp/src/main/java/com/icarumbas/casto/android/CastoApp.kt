package com.icarumbas.casto.android

import android.app.Application
import com.icarumbas.casto.di.appDI
import com.icarumbas.casto.di.setupAppDi
import com.icarumbas.casto.platformSpecific.PlatformConfiguration
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class CastoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupAppDi(PlatformConfiguration(this))
        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        } else {
            // Others(Release build)
        }
    }
}