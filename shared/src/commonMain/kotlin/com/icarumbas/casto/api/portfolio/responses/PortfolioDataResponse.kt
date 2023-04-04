package com.icarumbas.casto.api.portfolio.responses

import kotlinx.serialization.Serializable

@Serializable
data class PortfolioDataResponse(
    val coins: List<PortfolioCoinInfoResponse>
)