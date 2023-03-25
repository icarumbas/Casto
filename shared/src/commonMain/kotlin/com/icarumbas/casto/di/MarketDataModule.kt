package com.icarumbas.casto.di

import MarketDataRepository
import com.icarumbas.casto.CoinCapApiIdsQueries
import com.icarumbas.casto.Database
import com.icarumbas.casto.MarketDataQueries
import com.icarumbas.casto.api.market.CoinCapMarketDataApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val marketDataModule = DI.Module("marketDataModule") {
    bind<CoinCapApiIdsQueries>() with singleton {
        instance<Database>().coinCapApiIdsQueries
    }
    bind<MarketDataQueries>() with singleton {
        instance<Database>().marketDataQueries
    }
    bind<CoinCapMarketDataApi>() with singleton {
        CoinCapMarketDataApi(instance())
    }
    bind<MarketDataRepository>() with singleton {
        MarketDataRepository(instance(), instance(), instance(), instance())
    }
}
