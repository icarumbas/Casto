package com.icarumbas.casto.api.market

import com.icarumbas.casto.api.market.models.CoinCapSearchAssetResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val BASE_URL = "https://api.coincap.io/v2"
private const val SEARCH_PARAM = "search"

class CoinCapMarketDataApi(
    private val client: HttpClient,
) {

    suspend fun getCoinCapAsset(id: String): CoinCapSearchAssetResponse? {
        val response = client.get(BASE_URL) {
            url {
                appendPathSegments("assets", id)
            }
        }

        return if (response.status.isSuccess()) {
            try {
                response.body()
            } catch (e: Throwable) {
                val responseBody = response.body<String>()
                Napier.e("$responseBody.", e)
                null
            }
        } else {
            Napier.e("Error getCoinCapAsset with response: $response")
            null
        }
    }

    suspend fun searchCoinCapAssets(ticker: String): CoinCapSearchAssetResponse? {
        val response = client.get(BASE_URL) {
            url {
                appendPathSegments("/assets")
                parameters.append(SEARCH_PARAM, ticker)
            }
        }
        return if (response.status.isSuccess()) {
            try {
                response.body()
            } catch (e: Throwable) {
                val responseBody = response.body<String>()
                Napier.e("$responseBody.", e)
                null
            }
        } else {
            Napier.e("Error searchCoinCapAssets with response: $response")
            null
        }
    }
}