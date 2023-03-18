package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.binance.models.BinanceUserCoinResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class BinanceApi(
    private val client: HttpClient,
    private val binanceRequestBuilder: BinanceRequestBuilder
) {

    suspend fun getUserCoins(): List<BinanceUserCoinResponse> {
        val request = binanceRequestBuilder
            .sapiRequest("capital/config/getall")
        val response = client.get(request)

        return if (response.status.isSuccess()) {
            response.body()
        } else {
            val reqData = request.build().toString()
            Napier.e("Error getUserCoins with request: $reqData")
            emptyList()
        }
    }
}