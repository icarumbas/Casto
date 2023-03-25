package com.icarumbas.casto.api.icons

interface IconsService {
    suspend fun getIcon(ticker: String): ByteArray?
}