package com.icarumbas.casto.di

import com.icarumbas.casto.api.binance.BinanceApi
import com.icarumbas.casto.api.binance.BinanceRequestBuilder
import com.icarumbas.casto.storage.binance.BinanceSecureKeyProvider
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val binanceModule = DI.Module("binanceModule") {
    importAll(coreApiModule, utilsModule, storageModule)
    bind<BinanceSecureKeyProvider>() with singleton {
        BinanceSecureKeyProvider(storage = instance())
    }
    bind<BinanceRequestBuilder>() with singleton {
        BinanceRequestBuilder(currentTimeProvider = instance(), secureKeyProvider = instance())
    }
    bind<BinanceApi>() with singleton {
        BinanceApi(client = instance(), binanceRequestBuilder = instance())
    }
}