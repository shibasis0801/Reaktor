package app.mehmaan.core.actor

import app.mehmaan.core.network.Response
import io.vertx.core.impl.logging.Logger
import io.vertx.core.impl.logging.LoggerFactory
import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

typealias Handler = suspend (routingContext: RoutingContext, coroutineScope: CoroutineScope) -> Response

open class BaseVerticle: CoroutineVerticle() {
    val verticleScope by lazy { CoroutineScope(coroutineContext) }
    val logger: Logger = LoggerFactory.getLogger(BaseVerticle::class.java)

    // Expose status codes, error/success, other sealed classes etc
    // Allow interception of routingContext
    inline fun Route._handle(
        crossinline handler: Handler,
        dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) = produces("application/json").handler {
        verticleScope.launch(dispatcher) {
            val result = handler(it, this)
            it.response()
                .putHeader("content-type", "application/json")
                .setStatusCode(result.statusCode.code)
                .end(result.jsonData)
        }
    }
    inline fun Route.handle(
        crossinline handler: Handler,
    ) = _handle(handler, vertx.dispatcher())

    inline fun Route.handleAsync(
        crossinline handler: Handler,
    ) = _handle(handler, Dispatchers.IO)
}

