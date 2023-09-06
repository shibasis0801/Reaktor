package app.mehmaan.mobile.adapter

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.KeyEvent
import app.mehmaan.core.framework.Adapter
import app.mehmaan.core.framework.AndroidAdapter
import app.mehmaan.core.framework.BaseActivity
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import kotlin.random.Random

// Needs improvements
class ReactAdapter(
    activity: BaseActivity
): AndroidAdapter<ReactAdapter>(activity), DefaultHardwareBackBtnHandler {

    private val code = Random.nextInt(100, 200)

    var rootView: ReactRootView? = null
    var instanceManager: ReactInstanceManager? = null

    fun requestPermission(activity: BaseActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(activity)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package: ${activity.packageName}")
                )
                activity.startActivityForResult(intent, code);
            }
        }
    }

    fun onPermissionResult(activity: BaseActivity, requestCode: Int, resultCode: Int, data: Intent?) {
        instanceManager?.onActivityResult(activity, requestCode, resultCode, data)
    }

    override fun onCreate(activity: BaseActivity) {
        SoLoader.init(activity, false)
    }

    fun start(
        appName: String,
        packages: List<ReactPackage>
    ) = invoke {
        rootView = ReactRootView(this)
        instanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(true)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        rootView?.startReactApplication(instanceManager, appName, null)
//        requestPermission(this)
    }

    override fun onResume(activity: BaseActivity) {
        instanceManager?.onHostResume(activity, this)
    }


    override fun onStop(activity: BaseActivity) {
        super.onStop(activity)
    }

    override fun onPause(activity: BaseActivity) {
        instanceManager?.onHostPause(activity)
        rootView?.unmountReactApplication()
    }

    override fun onDestroy(activity: BaseActivity) {
        instanceManager?.onHostDestroy(activity)
    }

    override fun onBackPressed(activity: BaseActivity) {
        instanceManager?.onBackPressed()
    }

    override fun invokeDefaultOnBackPressed() {
        invoke { onBackPressed() }
    }

    override fun onKeyUp(activity: BaseActivity, keyCode: Int, event: KeyEvent?) = run {
        if (keyCode == KeyEvent.KEYCODE_MENU && instanceManager != null) {
            instanceManager?.showDevOptionsDialog()
            true
        } else false
    }
}
