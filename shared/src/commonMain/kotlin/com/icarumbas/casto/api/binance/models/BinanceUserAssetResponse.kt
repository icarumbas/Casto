package com.icarumbas.casto.api.binance.models

import kotlinx.serialization.Serializable

/*
* {
    "asset": "AVAX",
    "free": "1",
    "locked": "0",
    "freeze": "0",
    "withdrawing": "0",
    "ipoable": "0",
    "btcValuation": "0"
  }
**/

@Serializable
data class BinanceUserAssetResponse(
    val asset: String,
    val free: Double,
    val locked: Double,
    val freeze: Double,
)