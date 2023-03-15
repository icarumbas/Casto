package com.icarumbas.casto.platformSpecific.data

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient