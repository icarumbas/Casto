package com.icarumbas.casto.models

data class DomainCoin(
    val iconPath: String?,
    val ticker: String,
    val price: Double,
    val priceChangePercent24: Double,
    val holdings: Double,
    val holdingsPrice: Double
)