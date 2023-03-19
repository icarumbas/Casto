package com.icarumbas.casto.platformSpecific

import org.kodein.di.DI

expect class PlatformConfiguration {

    fun getPlatformDiModule(): DI.Module?
}