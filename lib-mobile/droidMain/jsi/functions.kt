@file:Suppress("KotlinJniMissingFunction")
package app.mehmaan.mobile.jsi

import com.facebook.jni.HybridData


actual class NoArgNativeFunction(var mHybridData: HybridData) {
    private external fun nativeInvoke(): Any
    actual operator fun invoke() = nativeInvoke()
}

actual class SingleArgNativeFunction(var mHybridData: HybridData) {
    private external fun nativeInvoke(data: Any): Any
    actual operator fun invoke(data: Any) = nativeInvoke(data)
}
