package com.icarumbas.casto.platformSpecific

import android.content.Context
import okio.FileSystem
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

actual class PlatformConfigurationImpl(
    private val context: Context
) : PlatformConfiguration {

    override val getPlatformDiModule: DI.Module
        get() = DI.Module("platformConfiguration") {
            bind<Context>() with singleton {
                this@PlatformConfigurationImpl.context
            }
        }
}