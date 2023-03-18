package com.icarumbas.casto.repository

import com.icarumbas.casto.api.binance.BinanceApi
import com.icarumbas.casto.storage.models.StorageCoin

interface CoinsRepository {

    suspend fun getAllCoins(): List<StorageCoin>
}

class CoinsRepositoryImpl(
    private val binanceApi: BinanceApi
) : CoinsRepository {

    override suspend fun getAllCoins(): List<StorageCoin>  {
        return binanceApi.getUserAssets()
            .map { asset ->
                StorageCoin(
                    ticker = asset.asset,
                    price = 1.0,
                    holdings = asset.free + asset.locked,
                    holdingsPrice = 1.0
                )
            }
    }
}