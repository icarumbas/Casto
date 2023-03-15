package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.binance.models.BinanceUserCoinResponse
import io.ktor.client.*
import io.ktor.client.request.*

class BinanceApi(
    private val client: HttpClient,
) {

    suspend fun getUserCoins(): List<BinanceUserCoinResponse> {
        val response = client.get("/sapi/v1/capital/config/getall") {

        }
        return emptyList()
    }


}