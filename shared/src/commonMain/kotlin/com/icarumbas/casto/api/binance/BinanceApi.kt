package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.binance.models.BinanceUserAssetResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


class BinanceApi(
    private val client: HttpClient,
    private val binanceRequestBuilder: BinanceRequestBuilder
) {

    suspend fun getUserAssets(): List<BinanceUserAssetResponse> {
        val request = binanceRequestBuilder
            .sapiRequest("asset/getUserAsset")
        val response = client.post(request)

        return if (response.status.isSuccess()) {
            try {
                response.body()
            } catch (e: Throwable) {
                val responseBody = response.body<String>()
                Napier.e("$responseBody.", e)
                emptyList()
            }
        } else {
            Napier.e("Error getUserCoins: ${response.body<String>()}")
            emptyList()
        }
    }
}