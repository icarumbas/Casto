package com.icarumbas.casto.platformSpecific

import android.content.Context
import org.kodein.di.DI
import org.kodein.di.bind

actual class PlatformConfiguration(
    private val context: Context
) {

    actual fun getPlatformDiModule(): DI.Module? {
        return DI.Module("platformConfiguration") {
            bind<Context>(context)
        }
    }
}