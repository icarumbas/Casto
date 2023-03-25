package com.icarumbas.casto.repository

import com.icarumbas.casto.api.binance.BinanceApi
import com.icarumbas.casto.api.icons.IconsService
import com.icarumbas.casto.api.market.MarketDataService
import com.icarumbas.casto.storage.icons.IconsStorage
import com.icarumbas.casto.storage.models.StorageCoin

interface CoinsRepository {

    suspend fun getAllCoins(): List<StorageCoin>
}

class CoinsRepositoryImpl(
    private val binanceApi: BinanceApi,
    private val iconsService: IconsService,
    private val iconsStorage: IconsStorage,
    private val marketDataService: MarketDataService,
) : CoinsRepository {

    override suspend fun getAllCoins(): List<StorageCoin>  {
        return binanceApi.getUserAssets()
            .map { asset ->
                StorageCoin(
                    iconPath = getIconPath(asset.asset),
                    ticker = asset.asset,
                    price = 1.0,
                    holdings = asset.free + asset.locked,
                    holdingsPrice = 1.0
                )
            }
    }

    private suspend fun getIconPath(ticker: String): String? {
        if (iconsStorage.exists(ticker)) {
            return iconsStorage.getRelativePath(ticker)
        } else {
            val data = iconsService.getIcon(ticker)
            if (data != null) {
                iconsStorage.save(ticker, data)
                return iconsStorage.getRelativePath(ticker)
            } else {
                return null
            }
        }
    }
}