package com.icarumbas.casto.models

data class DomainMarketData(
    val ticker: String,
    val changePercent24Hr: Float,
    val priceUsd: Double,
    val lastUpdate: Long,
)