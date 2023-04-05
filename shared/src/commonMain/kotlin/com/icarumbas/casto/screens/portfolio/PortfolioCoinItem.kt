package com.icarumbas.casto.screens.portfolio

data class PortfolioCoinItem(
    val iconPath: String?,
    val ticker: String,
    val price: String,
    val holdings: String,
    val holdingsPrice: String,
    val priceChangePercent: String,
    val priceIncrease: Boolean,
)