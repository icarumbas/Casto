package com.icarumbas.casto.storage.binance

import com.icarumbas.casto.storage.SecureKeyProvider
import com.icarumbas.casto.storage.SecureKeyStorage


class BinanceSecureKeyProvider(private val storage: SecureKeyStorage) : SecureKeyProvider {

    override suspend fun getPublicKey(): String {
        return "UxrWvc6moNEClFXXiam6243wHC8FJL3Th17nwqzf7eKbd1dSHoOQ30Wl7b4FkJaV"
    }

    override suspend fun getPrivateKey(): String {
        return "X6dH4M0xxw5blzz9ejkHiVH28op03EFxTbEUUa4AzaxpqffUeDk9fTnQcFvC3WYp"
    }
}