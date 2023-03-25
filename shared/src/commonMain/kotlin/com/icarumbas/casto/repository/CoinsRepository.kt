package com.icarumbas.casto.repository

import MarketDataRepository
import com.icarumbas.casto.api.binance.BinanceApi
import com.icarumbas.casto.api.icons.IconsApi
import com.icarumbas.casto.storage.icons.IconsStorage
import com.icarumbas.casto.models.DomainCoin


class CoinsRepository(
    private val binanceApi: BinanceApi,
    private val iconsApi: IconsApi,
    private val iconsStorage: IconsStorage,
    private val marketDataRepository: MarketDataRepository,
) {

    suspend fun getAllCoins(): List<DomainCoin>  {
        return binanceApi.getUserAssets()
            .map { asset ->
                // todo handle nullable
                val marketData = marketDataRepository.getMarketData(asset.asset)!!
                DomainCoin(
                    iconPath = getIconPath(asset.asset),
                    ticker = asset.asset,
                    price = marketData.priceUsd,
                    priceChangePercent24 = marketData.changePercent24Hr,
                    holdings = asset.free + asset.locked,
                    holdingsPrice = (asset.free + asset.locked) * marketData.priceUsd
                )
            }
    }

    private suspend fun getIconPath(ticker: String): String? {
        if (iconsStorage.exists(ticker)) {
            return iconsStorage.getRelativePath(ticker)
        } else {
            val data = iconsApi.getIcon(ticker)
            if (data != null) {
                iconsStorage.save(ticker, data)
                return iconsStorage.getRelativePath(ticker)
            } else {
                return null
            }
        }
    }
}