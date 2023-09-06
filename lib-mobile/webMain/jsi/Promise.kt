package app.mehmaan.mobile.jsi

import kotlin.js.Promise as JSPromise

actual class Promise actual constructor(executor: suspend Promise.() -> Unit) {
    lateinit var resolveFn: (Any) -> Unit
    lateinit var rejectFn: (Throwable) -> Unit

    private val promise = JSPromise { resolve, reject ->
        this.resolveFn = resolve
        this.rejectFn = reject
    }

    actual fun resolve(value: Any) {
        resolveFn(value)
    }

    actual fun reject(error: Error) {
        rejectFn(error)
    }
}