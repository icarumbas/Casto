package com.icarumbas.casto.models

import com.icarumbas.casto.CoinsInfo
import com.icarumbas.casto.CoinsPrice
import com.icarumbas.casto.HoldingsInfo

data class PortfolioCoinData(
    val info: CoinsInfo,
    val price: CoinsPrice,
    val holdingsInfo: HoldingsInfo,
    val iconPath: String?,
)