package com.icarumbas.casto.api.binance.models

import kotlinx.serialization.Serializable

@Serializable
data class BinanceUserCoinResponse(
    val coin: String,
    val free: Double,
)