package concurrency

import kotlinx.coroutines.*

object Dispatch {
    private val scope = CoroutineScope(SupervisorJob())

    val Background = Dispatchers.IO
    val BackgroundExecutor = Background.asExecutor()

    val Main = Dispatchers.Main
    val MainExecutor = Main.asExecutor()

    suspend fun<Result> onMain(fn: suspend () -> Result) = scope.async(Main) { fn() }

    suspend fun<Result> inBackground(fn: suspend () -> Result) = scope.async(Background) { fn() }

    fun cancel() = scope.cancel()
}