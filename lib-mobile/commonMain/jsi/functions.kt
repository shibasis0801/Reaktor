@file:Suppress("KotlinJniMissingFunction")
package app.mehmaan.mobile.jsi
expect class NoArgNativeFunction {
    operator fun invoke(): Any
}


expect class SingleArgNativeFunction {
    operator fun invoke(data: Any): Any
}
