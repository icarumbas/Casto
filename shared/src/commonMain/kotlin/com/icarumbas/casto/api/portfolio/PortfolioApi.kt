package com.icarumbas.casto.api.portfolio

import com.icarumbas.casto.api.models.KeyCredentialsRequest
import com.icarumbas.casto.api.portfolio.responses.PortfolioDataResponse
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val BASE_URL = "https://casto.fly.dev/portfolio"
private const val ID_PARAM = "id"

class PortfolioApi(
    private val client: HttpClient,
) {
    suspend fun getPortfolio(id: String): PortfolioDataResponse? {
        val response = client.get("$BASE_URL/holdings") {
            url.parameters.append(ID_PARAM, id)
        }
        return if (response.status.isSuccess()) {
            response.body()
        } else {
            Napier.e("Error getPortfolio with response: $response")
            null
        }
    }

    suspend fun saveBinanceCredentials(
        id: String,
        publicKey: String,
        privateKey: String,
    ): Boolean {
        val response = client.post("$BASE_URL/save-binance-credentials") {
            url.parameters.append(ID_PARAM, id)
            contentType(ContentType.Application.Json)
            setBody(KeyCredentialsRequest(publicKey, privateKey))
        }
        return response.status.isSuccess()
    }
}