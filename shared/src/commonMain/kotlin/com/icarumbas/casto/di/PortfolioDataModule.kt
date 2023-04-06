package com.icarumbas.casto.di

import PortfolioService
import PortfolioServiceImpl
import com.icarumbas.casto.CoinsInfoQueries
import com.icarumbas.casto.CoinsPriceQueries
import com.icarumbas.casto.Database
import com.icarumbas.casto.HoldingsInfoQueries
import com.icarumbas.casto.api.portfolio.PortfolioApi
import io.ktor.util.reflect.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val marketDataModule = DI.Module("portfolioModule") {

    bind<PortfolioApi>() with singleton {
        PortfolioApi(client = instance())
    }
    bind<HoldingsInfoQueries>() with singleton {
        val db = instance<Database>()
        db.holdingsInfoQueries
    }
    bind<CoinsInfoQueries>() with singleton {
        val db = instance<Database>()
        db.coinsInfoQueries
    }
    bind<CoinsPriceQueries>() with singleton {
        val db = instance<Database>()
        db.coinsPriceQueries
    }
    bind<PortfolioService>() with singleton {
        PortfolioServiceImpl(
            portfolioApi = instance(),
            userApi = instance(),
            iconsService = instance(),
            holdingsInfoQueries = instance(),
            coinsInfoQueries = instance(),
            coinsPriceQueries = instance(),
            userSettings = instance(),
            database = instance(),
        )
    }
}
