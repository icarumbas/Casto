package com.icarumbas.casto.platformSpecific

import com.icarumbas.casto.platformSpecific.data.DatabaseDriverFactory
import com.icarumbas.casto.platformSpecific.data.SettingsFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

actual class PlatformConfigurationImpl : PlatformConfiguration {

    override val getPlatformDiModule: DI.Module
        get() = DI.Module("platformConfiguration") {
            bind<DatabaseDriverFactory>() with singleton {
                DatabaseDriverFactory()
            }
            bind<SettingsFactory>() with singleton {
                SettingsFactory()
            }
        }
}