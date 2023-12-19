package app.mehmaan.mobile.jsi

import dev.shibasis.reaktor.native.getSum
import kotlinx.cinterop.ExperimentalForeignApi

actual class NoArgNativeFunction(val nativeInvoker: (() -> Any)) {
    actual inline operator fun invoke() = nativeInvoker()
}

actual class SingleArgNativeFunction(val nativeInvoker: ((Any) -> Any)) {
    actual operator fun invoke(data: Any) = nativeInvoker(data)
}

@OptIn(ExperimentalForeignApi::class)
fun t() {
    getSum()
}
