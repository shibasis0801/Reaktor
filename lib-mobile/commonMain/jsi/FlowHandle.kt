package app.mehmaan.mobile.jsi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

expect class FlowHandle(
    flow: Flow<Any>
) {
    fun stop()
}

fun nativeFlow(fn: suspend FlowCollector<Any>.() -> Unit) = FlowHandle(flow(fn))
fun Flow<Any>.toNativeFlow() = FlowHandle(this)
