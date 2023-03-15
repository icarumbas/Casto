package com.icarumbas.casto

import com.icarumbas.casto.platformSpecific.crypto.bytesToHexUTF8
import junit.framework.TestCase.assertEquals
import org.junit.Test
import com.icarumbas.casto.platformSpecific.crypto.hmacSha256

class HmacTests {

    @Test
    fun testHMAC_SHA256() {
        val hmac = hmacSha256("The quick brown fox jumps over the lazy dog", "key")
        val signature = bytesToHexUTF8(hmac)
        val expected = "f7bc83f430538424b13298e6aa6fb143ef4d59a14946175997479dbc2d1a3cd8"
        assertEquals(signature, expected)
    }
}