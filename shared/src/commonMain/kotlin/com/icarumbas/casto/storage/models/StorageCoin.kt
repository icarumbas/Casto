package com.icarumbas.casto.storage.models

data class StorageCoin(
    val iconPath: String?,
    val ticker: String,
    val price: Double,
    val holdings: Double,
    val holdingsPrice: Double
)