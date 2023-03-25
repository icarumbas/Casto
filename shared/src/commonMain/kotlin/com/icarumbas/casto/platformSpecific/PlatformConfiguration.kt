package com.icarumbas.casto.platformSpecific

import org.kodein.di.DI

interface PlatformConfiguration {
    val getPlatformDiModule : DI.Module
}

expect class PlatformConfigurationImpl : PlatformConfiguration