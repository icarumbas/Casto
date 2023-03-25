package com.icarumbas.casto.api.market

import com.icarumbas.casto.CoinCapApiIdsQueries
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val BASE_URL = "api.coincap.io/v2"
private const val SEARCH_PARAM = "search"

class CoinCapMarketDataService(
    private val coinCapIdsQueries: CoinCapApiIdsQueries,
    private val client: HttpClient,
) : MarketDataService {

    override suspend fun getCoinPrice(ticker: String): Double {
        val coinCapId = coinCapIdsQueries.selectByTicker(ticker).executeAsOneOrNull()?.id

    }

    private suspend fun getTickerId(ticker: String): String {
        val response = client.get(BASE_URL) {
            url {
                appendPathSegments("/assets")
                parameters.append(SEARCH_PARAM, ticker)
            }
        }
        val
    }
}