package com.icarumbas.casto.api.binance

import com.icarumbas.casto.platformSpecific.crypto.bytesToHexUTF8
import com.icarumbas.casto.platformSpecific.crypto.hmacSha256

fun createBinanceSignature(
    requestMap: Map<String, String>,
    timestamp: Long,
    secret: String,
): String {
    val params = requestMap.entries.joinToString(separator = "&") { pair ->
        "${pair.key}=${pair.value}"
    }
    val message = "$params&timestamp=$timestamp"
    val hmac = hmacSha256(message, secret)
    return bytesToHexUTF8(hmac)
}