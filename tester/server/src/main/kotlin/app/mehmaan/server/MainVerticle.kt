package app.mehmaan.core.server

import app.mehmaan.core.actor.BaseVerticle
import app.mehmaan.core.network.JsonResponse
import app.mehmaan.core.network.StatusCode
import app.mehmaan.core.network.httpClient
import io.ktor.client.request.*
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.coroutines.await
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

@Serializable
data class FileUploadResponse(
  val length: Int
)

class MainVerticle: BaseVerticle() {
  private lateinit var server: HttpServer
  lateinit var router: Router

  val storedStrings = mutableListOf<String>()

  override suspend fun start() {
    server = vertx.createHttpServer()
    router = Router.router(vertx)

    router.apply {
      route().handler {
        val request: HttpServerRequest = it.request()
        val requestPath = request.path()
        val requestMethod = request.method().name()

        logger.info("Received request: $requestMethod $requestPath")
        it.next()
      }
      route().handler(BodyHandler.create())

      get("/")
        .handle { ctx, _ ->
          JsonResponse(mapOf("status" to "UP"))
        }

      get("/get-array")
        .handle { ctx, _ ->
          JsonResponse(mapOf("strings" to storedStrings))
        }

      post("/save-array")
        .handle { ctx, _ ->
          val strings = ctx.body().asJsonArray()
          logger.info("Received array: $strings")
          if (strings == null)
            JsonResponse(mapOf("error" to "Need a JSON array of strings"), StatusCode.ERROR_CLIENT)
          else {
            if (storedStrings.size > 500) {
              storedStrings.clear()
            }
            strings.forEach { storedStrings.add(it as String) }
            JsonResponse(mapOf("status" to "Cleared and stored"))
          }
        }

      post("/post-upload")
        .handle { ctx, _ ->
          val response = ctx.response()
          val data = ctx.request().getFormAttribute("data")
          val image = ctx.fileUploads().find { it.name() == "image" }
          if (data == null || image == null) {
            JsonResponse(FileUploadResponse(-1), StatusCode.ERROR_CLIENT)
          }
          else {
            val file = vertx.fileSystem().readFile(image.uploadedFileName()).await()
            println("File Length: ${file.length()}")
            println("Data: $data")
            JsonResponse(FileUploadResponse(file.length()))
          }
        }

      get("/cloudflare-latency")
        .handle { _, _ ->
          // I want to benchmark the next line, start time, end time, time taken, etc
            val time = measureTimeSuspend {
              val response = httpClient.get("https://mehmaan-post.shibasispatnaik.workers.dev/2023-07-31-13-27-12-924.jpg-compressed")
            }
            JsonResponse(mapOf("timeTaken" to time))
        }
    }

    val port = System.getenv("PORT")?.toInt() ?: 8080
    server.requestHandler(router).listen(port)
    logger.info("Listening to requests at $port")
  }
}

@OptIn(ExperimentalTime::class)
suspend fun measureTimeSuspend(block: suspend () -> Unit): Long {
  val timeSource = TimeSource.Monotonic
  val mark = timeSource.markNow()
  block()
  return mark.elapsedNow().inWholeMilliseconds
}