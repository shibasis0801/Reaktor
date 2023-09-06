package app.mehmaan.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual val httpClient = HttpClient(OkHttp) {
    engine {
        config {
            followRedirects(true)
        }
//        addInterceptor(interceptor)
//        addNetworkInterceptor(interceptor)
//
//        preconfigured = okHttpClientInstance
    }
}