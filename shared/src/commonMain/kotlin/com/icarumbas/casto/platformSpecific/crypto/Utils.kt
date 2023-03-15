package com.icarumbas.casto.platformSpecific.crypto

expect fun hmacSha256(
    msg: String,
    key: String,
): ByteArray

expect fun bytesToHexUTF8(bytes: ByteArray): String