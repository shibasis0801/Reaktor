package app.mehmaan.mobile.jsi

import dev.shibasis.reaktor.native.getSum

actual class NoArgNativeFunction(val nativeInvoker: (() -> Any)) {
    actual inline operator fun invoke() = nativeInvoker()
}

actual class SingleArgNativeFunction(val nativeInvoker: ((Any) -> Any)) {
    actual operator fun invoke(data: Any) = nativeInvoker(data)
}

fun t() {
    getSum()
}
