package com.icarumbas.casto.di

import com.icarumbas.casto.CoinCapApiIdsQueries
import com.icarumbas.casto.Database
import com.icarumbas.casto.api.market.CoinCapMarketDataService
import com.icarumbas.casto.api.market.MarketDataService
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val coinCapModule = DI.Module("coinCapModule") {
    bind<CoinCapApiIdsQueries>() with singleton {
        instance<Database>().coinCapApiIdsQueries
    }
    bind<MarketDataService>() with singleton {
        CoinCapMarketDataService(instance())
    }
}
