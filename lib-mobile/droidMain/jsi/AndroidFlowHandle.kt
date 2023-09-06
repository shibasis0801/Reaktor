package app.mehmaan.mobile.jsi

import concurrency.Dispatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

actual class FlowHandle actual constructor(
    flow: Flow<Any>
) {
    var resolver: SingleArgNativeFunction? = null

    private val scope = CoroutineScope(Dispatch.Background)
    private val flowJob: Job = scope.launch {
        // we can replay if needed
        flow.collect {
            resolver?.invoke(it)
        }
    }
    actual fun stop() {
        flowJob.cancel()
    }
}
