package app.mehmaan.core.framework

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

actual val __PLATFORM = PlatformType.DARWIN

actual class WeakRef<T> actual constructor(referred: T) {
    @OptIn(ExperimentalNativeApi::class)
    val ref = referred?.let { WeakReference(referred) }
    @OptIn(ExperimentalNativeApi::class)
    actual fun get() = ref?.get()
}
