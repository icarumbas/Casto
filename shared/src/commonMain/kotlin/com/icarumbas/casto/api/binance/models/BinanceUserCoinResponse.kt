package com.icarumbas.casto.api.binance.models

@kotlinx.serialization.Serializable
data class BinanceUserCoinResponse(
    val coin: String,
    val free: Double,
)