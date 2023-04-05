package com.icarumbas.casto.api.icons

interface IconsApi {
    suspend fun getIcon(ticker: String): ByteArray?
}