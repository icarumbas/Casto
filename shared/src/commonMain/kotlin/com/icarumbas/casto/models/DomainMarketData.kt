package com.icarumbas.casto.models

import com.icarumbas.casto.MarketData
import com.icarumbas.casto.api.market.models.CoinCapAssetResponse

data class DomainMarketData(
    val ticker: String,
    val changePercent24Hr: Double,
    val priceUsd: Double
) {

    companion object {
        fun fromStorage(marketData: MarketData) =
            DomainMarketData(
                marketData.ticker,
                marketData.changePercent24Hr,
                marketData.priceUsd
            )

        fun fromCoinCapResponse(response: CoinCapAssetResponse) =
            DomainMarketData(
                response.symbol,
                response.changePercent24Hr.toDouble(),
                response.priceUsd.toDouble()
            )
    }
}

