package app.mehmaan.core.network

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Move to common
enum class StatusCode(val code: Int) {
    SUCCESS(200),
    ERROR_CLIENT(400),
    ERROR_RATE_LIMIT(429),
    ERROR_SERVER(500)
}

data class Response(
    val jsonData: String,
    val statusCode: StatusCode = StatusCode.SUCCESS
)

inline fun<reified T> JsonResponse(
    data: T,
    statusCode: StatusCode = StatusCode.SUCCESS,
) = Response(Json.encodeToString<T>(data), statusCode)
