package app.mehmaan.mobile.jsi

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

actual class Promise actual constructor(val executor: suspend Promise.() -> Unit) {
    var nativeResolver: Resolver = null
    var nativeRejecter: Rejecter = null

    @OptIn(DelicateCoroutinesApi::class)
    fun setNativeResolver(resolver: Resolver) {
        GlobalScope.launch { executor() }
        nativeResolver = resolver

    }
    fun setNativeRejecter(rejecter: Rejecter) {
        nativeRejecter = rejecter
    }

    actual fun resolve(value: Any) {
        nativeResolver?.invoke(when (value) {
            is HashMap<*, *>, is Array<*>, is Number -> value
            else -> throw Error("Type not yet supported")
        })
    }

    actual fun reject(error: Error) {
        nativeRejecter?.invoke(error)
    }
}

