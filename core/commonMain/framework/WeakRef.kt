package app.mehmaan.core.framework

expect class WeakRef<T>(referred: T) {
    fun get(): T?
}