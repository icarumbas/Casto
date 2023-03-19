package com.icarumbas.casto.platformSpecific

import org.kodein.di.DI

actual class PlatformConfigurationImpl : PlatformConfiguration {

    override val getPlatformDiModule: DI.Module?
        get() = null
}