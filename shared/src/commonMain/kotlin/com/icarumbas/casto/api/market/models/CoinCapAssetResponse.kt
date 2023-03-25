package com.icarumbas.casto.api.market.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinCapAssetResponse(
    val id: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
)