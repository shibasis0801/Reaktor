package app.mehmaan.core.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual val network = HttpClient(Darwin) {

}