package app.mehmaan.mobile.jsi

import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl

@Suppress("KotlinJniMissingFunction")
@DoNotStrip
object Reaktor {
    external fun install(
        reactPointer: Long,
        jsCallInvokerHolder: CallInvokerHolderImpl,
        nativeCallInvokerHolder: CallInvokerHolderImpl
    )

    external fun addModule(
        module: ReaktorModule,
        name: String
    )

    init {
        System.loadLibrary("reaktor")
    }
}