package com.icarumbas.casto.android

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class CastoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Napier.base(DebugAntilog())
        } else {
            // Others(Release build)
        }
    }
}