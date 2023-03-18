package com.icarumbas.casto.api.binance

import com.icarumbas.casto.utils.CurrentTimeProvider
import com.icarumbas.casto.platformSpecific.crypto.bytesToHexUTF8
import com.icarumbas.casto.platformSpecific.crypto.hmacSha256
import com.icarumbas.casto.storage.binance.BinanceSecureKeyProvider
import io.ktor.client.request.*

private const val BINANCE_BASE_URL = "https://api.binance.com"
private const val BINANCE_SAPI_ENDPOINT = "sapi/v1"
private const val BINANCE_API_ENDPOINT = "api/v3"
private const val TIMESTAMP_KEY = "timestamp"
private const val APIKEY_HEADER = "X-MBX-APIKEY"
private const val SIGNATURE_PARAMETER = "signature"

class BinanceRequestBuilder(
    private val secureKeyProvider: BinanceSecureKeyProvider,
    private val currentTimeProvider: CurrentTimeProvider
) {

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
            val publicKey = secureKeyProvider.getPublicKey()
            val privateKey = secureKeyProvider.getPrivateKey()

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
            val hmac = hmacSha256(message, privateKey)
            val signature = bytesToHexUTF8(hmac)
            url.parameters.append(SIGNATURE_PARAMETER, signature)

            headers {
                append(APIKEY_HEADER, publicKey)
            }
        }
    }

}