package app.mehmaan.core.framework


actual val __PLATFORM = PlatformType.JS

// noop
actual class WeakRef<T> actual constructor(referred: T) {
    private val ref = referred
    actual fun get(): T? {
        return ref
    }
}