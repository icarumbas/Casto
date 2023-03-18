package com.icarumbas.casto.storage

interface SecureKeyProvider {
    suspend fun getPublicKey(): String
    suspend fun getPrivateKey(): String
}