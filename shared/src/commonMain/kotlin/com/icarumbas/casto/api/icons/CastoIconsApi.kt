package com.icarumbas.casto.api.icons

interface CastoIconsApi {
    suspend fun getIcon(ticker: String): ByteArray?
}