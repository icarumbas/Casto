package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.binance.models.BinanceUserCoinResponse
import io.ktor.client.*
import io.ktor.client.request.*


class BinanceApi(
    private val client: HttpClient,
    private val binanceRequestBuilder: BinanceRequestBuilder
) {

    suspend fun getUserCoins(): List<BinanceUserCoinResponse> {
        val response = client.get(binanceRequestBuilder.sapiRequest("capital/config/getall"))
        return emptyList()
    }



}