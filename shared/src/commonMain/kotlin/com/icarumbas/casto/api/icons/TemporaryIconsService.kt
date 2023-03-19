package com.icarumbas.casto.api.icons

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

private const val ICONS_BASE_URL = "https://cryptoicons.org/api/icon"
private const val ICON_SIZE = 32

class TemporaryIconsService(
    private val client: HttpClient
) : IconsService {

    override suspend fun getIcon(ticker: String): ByteArray? {
        val response = client.get("$ICONS_BASE_URL/$ticker/$ICON_SIZE")
        if (response.status.isSuccess()) {
            return response.bodyAsChannel().toByteArray()
        } else {
            return null
        }
    }
}