package com.icarumbas.casto.storage.binance

import com.icarumbas.casto.storage.SecureKeyProvider
import com.icarumbas.casto.storage.SecureKeyStorage


class BinanceSecureKeyProvider(private val storage: SecureKeyStorage) : SecureKeyProvider {

    override suspend fun getPrivateKey(): String {
        return "X6dH4M0xxw5blzz9ejkHiVH28op03EFxTbEUUa4AzaxpqffUeDk9fTnQcFvC3WYp"
    }
}