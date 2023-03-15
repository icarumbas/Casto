package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.CurrentTimeProvider
import com.icarumbas.casto.platformSpecific.crypto.bytesToHexUTF8
import com.icarumbas.casto.platformSpecific.crypto.hmacSha256
import io.ktor.client.request.*

private const val BINANCE_BASE_URL = "https://api.binance.com"
private const val BINANCE_SAPI_ENDPOINT = "sapi/v1"
private const val BINANCE_API_ENDPOINT = "api/v3"
private const val TIMESTAMP_KEY = "timestamp"
private const val SECRET_HEADER = "X-MBX-APIKEY"

class BinanceRequestBuilder(
    private val secretProvider: SecretKeyProvider,
    private val currentTimeProvider: CurrentTimeProvider
) {

    interface SecretKeyProvider {
        suspend fun getPrivateKey(): String
    }

    suspend fun apiRequest(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpRequestBuilder {
        val newUrl = "$BINANCE_BASE_URL/$BINANCE_API_ENDPOINT/$urlString"
        return baseRequest(newUrl, block)
    }

    suspend fun sapiRequest(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpRequestBuilder {
        val newUrl = "$BINANCE_BASE_URL/$BINANCE_SAPI_ENDPOINT/$urlString"
        return baseRequest(newUrl, block)
    }

    private suspend fun baseRequest(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpRequestBuilder {
        return HttpRequestBuilder().apply {
            url(urlString)
            block()
            url {
                parameters.removeKeysWithNoEntries()
                parameters.append(
                    TIMESTAMP_KEY,
                    currentTimeProvider.getCurrentTimeMils().toString()
                )
            }

            val message = url.parameters.names().joinToString(separator = "&") { name ->
                "${name}=${url.parameters[name]!!}"
            }
            val hmac = hmacSha256(message, secretProvider.getPrivateKey())
            val signature = bytesToHexUTF8(hmac)

            headers {
                append(SECRET_HEADER, signature)
            }
        }
    }

}