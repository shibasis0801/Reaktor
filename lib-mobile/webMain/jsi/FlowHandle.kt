package app.mehmaan.mobile.jsi

import kotlinx.coroutines.flow.Flow

actual class FlowHandle actual constructor(flow: Flow<Any>) {
    actual fun stop() {}
    actual fun sum() = 0
}