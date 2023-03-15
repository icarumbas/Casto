package com.icarumbas.casto.api

import com.icarumbas.casto.api.models.WalletAssetData

interface Wallet {

    fun getAssets(): List<WalletAssetData>
}