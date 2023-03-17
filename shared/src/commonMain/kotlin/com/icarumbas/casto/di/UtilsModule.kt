package com.icarumbas.casto.di

import com.icarumbas.casto.api.CurrentTimeProvider
import com.icarumbas.casto.api.DateTimeCurrentTimeProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val utilsModule = DI.Module("utilsModule") {
    bind<CurrentTimeProvider>() with singleton {
        DateTimeCurrentTimeProvider()
    }
}