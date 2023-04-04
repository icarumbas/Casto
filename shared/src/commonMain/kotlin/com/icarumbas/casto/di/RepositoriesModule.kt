package com.icarumbas.casto.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val repositoriesModule = DI.Module("repositoriesModule") {
    bind<CoinsRepository>() with singleton {
        CoinsRepository(instance(), instance(), instance(), instance())
    }
}