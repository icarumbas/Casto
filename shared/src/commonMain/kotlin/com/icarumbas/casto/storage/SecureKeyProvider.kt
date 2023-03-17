package com.icarumbas.casto.storage

interface SecureKeyProvider {
    suspend fun getPrivateKey(): String
}