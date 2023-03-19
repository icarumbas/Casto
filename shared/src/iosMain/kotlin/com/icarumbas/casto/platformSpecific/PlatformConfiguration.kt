package com.icarumbas.casto.platformSpecific

import org.kodein.di.DI

actual class PlatformConfiguration {

    actual fun getPlatformDiModule(): DI.Module? {
        return null
    }
}