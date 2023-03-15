package com.icarumbas.casto

import com.icarumbas.casto.api.binance.createBinanceSignature
import org.junit.Test
import kotlin.test.assertEquals

class BinanceSignatureTest {

    @Test
    fun testSampleSignature() {
        val requestMap = mapOf(
            "symbol" to "LTCBTC",
            "side" to "BUY",
            "type" to "LIMIT",
            "timeInForce" to "GTC",
            "quantity" to "1",
            "price" to "0.1",
            "recvWindow" to "5000",
        )
        val timestamp = 1499827319559L
        val secret = "NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j"
        val signature = createBinanceSignature(requestMap, timestamp, secret)
        val expected = "c8db56825ae71d6d79447849e617115f4a920fa2acdcab2b053c4b2838bd6b71"
        assertEquals(signature, expected)
    }
}