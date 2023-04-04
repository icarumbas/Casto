package com.icarumbas.casto.di

import PortfolioInteractor
import com.icarumbas.casto.api.portfolio.PortfolioApi
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val marketDataModule = DI.Module("marketDataModule") {
    bind<PortfolioApi>() with singleton {
        PortfolioApi(instance())
    }
    bind<PortfolioInteractor>() with singleton {
        PortfolioInteractor(instance(), instance(), instance(), instance())
    }
}
