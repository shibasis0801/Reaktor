package app.mehmaan.mobile.jsi

import app.mehmaan.core.framework.Async
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual class FlowHandle actual constructor(
    flow: Flow<Any>
) {
    var resolver: SingleArgNativeFunction? = null

    private val scope = CoroutineScope(Dispatchers.Async)
    private val flowJob: Job = scope.launch {
        // we can replay if needed
        flow.collect {
            resolver?.invoke(it)
        }
    }
    actual fun stop() {
        flowJob.cancel()
    }
    actual fun sum() = 0
}
