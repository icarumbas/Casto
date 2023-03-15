package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.binance.models.BinanceUserCoinResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*


class BinanceApi(
    private val client: HttpClient,
    private val binanceRequestBuilder: BinanceRequestBuilder
) {

    suspend fun getUserCoins(): List<BinanceUserCoinResponse> {
        return client.get(binanceRequestBuilder
            .sapiRequest("capital/config/getall"))
            .body()
    }
}