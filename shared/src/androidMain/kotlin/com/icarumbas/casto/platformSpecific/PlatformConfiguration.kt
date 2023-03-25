package com.icarumbas.casto.platformSpecific

import android.content.Context
import com.icarumbas.casto.platformSpecific.data.DriverFactory
import okio.FileSystem
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

actual class PlatformConfigurationImpl(
    private val context: Context
) : PlatformConfiguration {

    override val getPlatformDiModule: DI.Module
        get() = DI.Module("platformConfiguration") {
            bind<Context>() with singleton {
                this@PlatformConfigurationImpl.context
            }
            bind<DriverFactory>() with singleton {
                DriverFactory(instance())
            }
        }
}