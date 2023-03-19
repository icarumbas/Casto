package com.icarumbas.casto.di

import com.icarumbas.casto.api.icons.IconsService
import com.icarumbas.casto.api.icons.TemporaryIconsService
import com.icarumbas.casto.platformSpecific.data.httpClient
import io.ktor.client.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val coreApiModule = DI.Module("coreApiModule") {
    bind<HttpClient>() with singleton {
        httpClient()
    }
    bind<IconsService>() with singleton {
        TemporaryIconsService(instance())
    }
}