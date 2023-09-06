package app.mehmaan.core.framework

expect class WeakRef<T: Any>(referred: T) {
    fun get(): T?
}