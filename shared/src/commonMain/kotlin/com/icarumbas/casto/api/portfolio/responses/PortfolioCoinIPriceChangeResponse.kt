package com.icarumbas.casto.api.portfolio.responses

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioCoinIPriceChangeResponse(
    val changePercent1h: Float,
    val changePercent24h: Float,
    val changePercent7d: Float,
    val changePercent14d: Float,
    val changePercent30d: Float,
    val changePercent60d: Float,
    val changePercent1y: Float,
)