package com.icarumbas.casto.api.portfolio.responses

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioCoinInfoResponse(
    val id: String,
    val ticker: String,
    val name: String,
    val price: Float,
    val holdingsPrice: Float,
    val priceChangePercent: PortfolioCoinIPriceChangeResponse,
)