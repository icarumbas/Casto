package com.icarumbas.casto.android.portfolio

import androidx.compose.ui.graphics.ImageBitmap

data class PortfolioCoin(
    val icon: ImageBitmap,
    val ticker: String,
    val price: String,
    val holdings: String,
    val holdingsPrice: String
)