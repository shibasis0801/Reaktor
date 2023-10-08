package app.mehmaan.core.framework

import java.lang.ref.WeakReference

actual val __PLATFORM = PlatformType.ANDROID

actual class WeakRef<T> actual constructor(referred: T) {
    private val ref = WeakReference(referred)
    actual fun get() = ref.get()
}