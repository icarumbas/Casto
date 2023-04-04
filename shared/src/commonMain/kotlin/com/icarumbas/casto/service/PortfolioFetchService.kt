package com.icarumbas.casto.service

import com.icarumbas.casto.api.binance.BinanceApi

class PortfolioFetchService(
    private val binanceApi: BinanceApi,
) {

    suspend fun fetchBinancePortfolio() {
        binanceApi.getUserAssets()
            .map { asset ->
                asset.
            }
    }
}