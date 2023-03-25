package com.icarumbas.casto.di

import com.icarumbas.casto.platformSpecific.data.httpClient
import io.ktor.client.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val coreApiModule = DI.Module("coreApiModule") {
    bind<HttpClient>() with singleton {
        httpClient()
    }
}