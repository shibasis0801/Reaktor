package app.mehmaan.mobile.jsi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

actual class Promise actual constructor(val executor: suspend Promise.() -> Unit) {
    private val respondersSet = AtomicInteger(0)

    private fun tryInit() {
        if (respondersSet.get() >= 2) {
            throw Error("Promise already initialized, does not support changing resolver / rejecter")
        }
        respondersSet.incrementAndGet()
        if (respondersSet.get() == 2) {
            GlobalScope.launch { executor() }
        }
    }

    var resolver: SingleArgNativeFunction? = null
        set(value) {
            field = value
            tryInit()
        }
    var rejecter: SingleArgNativeFunction? = null
        set(value) {
            field = value
            tryInit()
        }



    actual fun resolve(value: Any) { resolver?.invoke(value) }
    actual fun reject(error: Error) { rejecter?.invoke(error) }
}

