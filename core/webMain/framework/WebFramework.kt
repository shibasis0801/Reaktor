package app.mehmaan.core.framework


// noop
actual class WeakRef<T: Any> actual constructor(referred: T) {
    val ref = referred
    actual fun get(): T? {
        return ref
    }
}