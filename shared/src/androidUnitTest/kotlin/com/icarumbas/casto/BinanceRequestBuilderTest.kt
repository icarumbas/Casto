package com.icarumbas.casto

import com.icarumbas.casto.utils.CurrentTimeProvider
import com.icarumbas.casto.api.binance.BinanceRequestBuilder
import com.icarumbas.casto.storage.binance.BinanceSecureKeyProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class BinanceRequestBuilderTest {

    @Test
    fun testSampleSignature() {
        val currentTimeProvider = object : CurrentTimeProvider {
            override fun getCurrentTimeMils(): Long {
                return 1499827319559L
            }
        }

        val mockSecureKeyProvider = mockk<BinanceSecureKeyProvider>()
        coEvery {
            mockSecureKeyProvider.getPrivateKey()
        } returns "NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j"

        val requestBuilder = BinanceRequestBuilder(mockSecureKeyProvider, currentTimeProvider)
        val requestData = runBlocking {
            requestBuilder.sapiRequest("") {
                url {
                    parameters.append("symbol", "LTCBTC")
                    parameters.append("side", "BUY")
                    parameters.append("type", "LIMIT")
                    parameters.append("timeInForce", "GTC")
                    parameters.append("quantity", "1")
                    parameters.append("price", "0.1")
                    parameters.append("recvWindow", "5000")
                }
            }.build()
        }

        val header = requestData.headers["X-MBX-APIKEY"]
        val expectedHeader = "c8db56825ae71d6d79447849e617115f4a920fa2acdcab2b053c4b2838bd6b71"
        assertEquals(header, expectedHeader)
    }
}