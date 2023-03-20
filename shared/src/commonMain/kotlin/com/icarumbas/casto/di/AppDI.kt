package com.icarumbas.casto.di

import com.icarumbas.casto.platformSpecific.PlatformConfiguration
import org.kodein.di.DI

private var _appDI: DI? = null

val appDI: DI
    get() = requireNotNull(_appDI)

fun setupAppDi(platformConfiguration: PlatformConfiguration) {
    _appDI = DI {
        platformConfiguration.getPlatformDiModule?.let {
            import(it)
        }
        importAll(coreApiModule,
            utilsModule,
            storageModule,
            repositoriesModule,
            binanceModule)
    }
}