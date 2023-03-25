package com.icarumbas.casto.platformSpecific

import com.icarumbas.casto.platformSpecific.data.DriverFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

actual class PlatformConfigurationImpl : PlatformConfiguration {

    override val getPlatformDiModule: DI.Module
        get() = DI.Module("platformConfiguration") {
            bind<DriverFactory>() with singleton {
                DriverFactory()
            }
        }
}