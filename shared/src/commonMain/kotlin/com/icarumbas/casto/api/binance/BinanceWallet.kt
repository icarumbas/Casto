package com.icarumbas.casto.api.binance

import com.icarumbas.casto.api.Wallet
import com.icarumbas.casto.api.models.WalletAssetData

class BinanceWallet : Wallet {

    override fun getAssets(): List<WalletAssetData> {
        return emptyList()
    }
}