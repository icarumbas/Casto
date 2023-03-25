package com.icarumbas.casto.api.market.models

import kotlinx.serialization.Serializable

@Serializable
data class CoinCapSearchAssetResponse(
    val data: List<CoinCapAssetResponse>
)