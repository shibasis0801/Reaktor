package app.mehmaan.mobile.jsi

import android.view.View
import app.mehmaan.mobile.mehmaan.MehmaanConfig
import com.facebook.proguard.annotations.DoNotStrip
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import dev.reaktor.core.annotations.reaktor.Expose

const val JSI_MANAGER = "JSIManager"

@Expose
class NetworkModule(): ReaktorModule {
    override val name = "NetworkModule"
    fun get() = nativeFlow {
        for (i in 1..10) {
            emit(i)
        }
    }
}

@DoNotStrip
@ReactModule(name = JSI_MANAGER)
class JSIManager(
    reactApplicationContext: ReactApplicationContext,
    var modules: List<ReaktorModule>
): ReactContextBaseJavaModule(reactApplicationContext) {
    override fun getName() = JSI_MANAGER

    @ReactMethod(isBlockingSynchronousMethod = true)
    fun install() {
        reactApplicationContextIfActiveOrWarn?.apply {
            Reaktor.install(
                javaScriptContextHolder.get(),
                catalystInstance.jsCallInvokerHolder as CallInvokerHolderImpl,
                catalystInstance.nativeCallInvokerHolder as CallInvokerHolderImpl
            )

            modules.forEach { Reaktor.addModule(it, it.name) }
            modules = listOf()
        }
    }
}

class ReaktorPackage(
    var additionalModules: List<ReaktorModule>
): ReactPackage {
    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): List<NativeModule> {
        return listOf(
            JSIManager(reactContext, listOf(
                NetworkModule(),
                MehmaanConfig()
            ) + additionalModules)
        )
    }

    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ) = mutableListOf<ViewManager<View, ReactShadowNode<*>>>()
}
