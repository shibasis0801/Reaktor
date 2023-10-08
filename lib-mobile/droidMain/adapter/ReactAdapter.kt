package app.mehmaan.mobile.adapter

//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.provider.Settings
//import android.view.KeyEvent
//import androidx.activity.ComponentActivity
//import app.mehmaan.core.framework.Adapter
//import app.mehmaan.core.framework.AndroidAdapter
//import app.mehmaan.mobile.jsi.ReaktorPackage
//import com.facebook.react.ReactInstanceManager
//import com.facebook.react.ReactPackage
//import com.facebook.react.ReactRootView
//import com.facebook.react.common.LifecycleState
//import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
//import com.facebook.soloader.SoLoader
//import kotlin.random.Random
//
//// Needs improvements
//class ReactAdapter(
//    activity: ComponentActivity
//): AndroidAdapter<ReactAdapter>(activity), DefaultHardwareBackBtnHandler {
//
//    private val code = Random.nextInt(100, 200)
//
//    var rootView: ReactRootView? = null
//    var instanceManager: ReactInstanceManager? = null
//
//    fun getReactView(): ReactRootView {
////        val packages = PackageList(application).packages
////        reactAdapter.start("Mehmaan", packages + getMehmaanPackage(this) + ReaktorPackage(listOf(feedViewModel)) + SafeAreaContextPackage())
//        return rootView ?: throw Exception("React root view is null")
//    }
//
//
//    fun requestPermission(activity: ComponentActivity) {
//        if(!Settings.canDrawOverlays(activity)) {
//            val intent = Intent(
//                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                Uri.parse("package: ${activity.packageName}")
//            )
//            activity.startActivityForResult(intent, code);
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        instanceManager?.onActivityResult(activity, requestCode, resultCode, data)
//    }
//
//    override fun onCreate(activity: ComponentActivity) {
//        SoLoader.init(activity, false)
//    }
//
//    fun start(
//        appName: String,
//        packages: List<ReactPackage>
//    ) = invoke {
//        rootView = ReactRootView(this)
//        instanceManager = ReactInstanceManager.builder()
//            .setApplication(application)
//            .setCurrentActivity(this)
//            .setJSMainModulePath("index")
//            .addPackages(packages)
//            .setUseDeveloperSupport(true)
//            .setInitialLifecycleState(LifecycleState.RESUMED)
//            .build()
//
//        rootView?.startReactApplication(instanceManager, appName, null)
////        requestPermission(this)
//    }
//
//    override fun onResume(activity: ComponentActivity) {
//        instanceManager?.onHostResume(activity, this)
//    }
//
//
//    override fun onStop(activity: ComponentActivity) {
//        super.onStop(activity)
//    }
//
//    override fun onPause(activity: ComponentActivity) {
//        instanceManager?.onHostPause(activity)
//        rootView?.unmountReactApplication()
//    }
//
//    override fun onDestroy(activity: ComponentActivity) {
//        instanceManager?.onHostDestroy(activity)
//    }
//
//    override fun onBackPressed(activity: ComponentActivity) {
//        instanceManager?.onBackPressed()
//    }
//
//    override fun invokeDefaultOnBackPressed() {
//        invoke { onBackPressed() }
//    }
//
//    override fun onKeyUp(activity: ComponentActivity, keyCode: Int, event: KeyEvent?) = run {
//        if (keyCode == KeyEvent.KEYCODE_MENU && instanceManager != null) {
//            instanceManager?.showDevOptionsDialog()
//            true
//        } else false
//    }
//}
