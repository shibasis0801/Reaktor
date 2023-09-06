package app.mehmaan.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.compose.ui.platform.ComposeView
import app.mehmaan.core.di.koins
import app.mehmaan.core.framework.BaseActivity
import app.mehmaan.feed.api.FeedPod
import app.mehmaan.feed.viewmodels.FeedViewModel
import app.mehmaan.media.camera.CameraComponent
import app.mehmaan.media.camera.CameraScreen
import app.mehmaan.media.di.ImageModule
import app.mehmaan.mobile.adapter.ReactAdapter
import app.mehmaan.mobile.jsi.ReaktorPackage
import app.mehmaan.navigation.route.MehmaanCompose
import app.mehmaan.navigation.route.MehmaanJunction
import com.facebook.react.PackageList
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.th3rdwave.safeareacontext.SafeAreaContextPackage
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class MainActivity : BaseActivity() {
    private lateinit var analytics: FirebaseAnalytics
    private val reactAdapter = ReactAdapter(this)
    private val cameraComponent by inject<CameraComponent>()


    private fun getMehmaanPackage() = object: ReactPackage {
        override fun createViewManagers(
            reactContext: ReactApplicationContext
        ) = listOf<SimpleViewManager<*>>(
//            ReaktorCamera(this@MainActivity) as SimpleViewManager<*>
        )

        override fun createNativeModules(
            reactContext: ReactApplicationContext
        ) = listOf<NativeModule>()
    }

    fun sendAppStartEvent() {
        analytics = Firebase.analytics
        analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "Mehmaan")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }

    fun getReactView(feedViewModel: FeedViewModel): ReactRootView {
        connect(reactAdapter)
        val packages = PackageList(application).packages
        reactAdapter.start("Mehmaan", packages + getMehmaanPackage() + ReaktorPackage(listOf(feedViewModel)) + SafeAreaContextPackage())
        return reactAdapter.rootView ?: throw Exception("React root view is null")
    }

    fun getComposeView(): ComposeView {
        return ComposeView(this).apply {
            setContent {
                CameraScreen(cameraComponent)
//                MehmaanCompose("feed/post/guuk")
            }
        }
    }


    class CustomView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        val composeView: ComposeView,
        val reactRootView: ReactRootView
    ) : FrameLayout(context, attrs, defStyleAttr) {
        init {
            composeView.visibility = VISIBLE
            reactRootView.visibility = GONE
            addView(composeView)
            addView(reactRootView)
            composeView.setOnClickListener {
                showReactRootView()
            }
        }

        var compose = false
        fun onBackPress(): Boolean {
            if (compose) showComposeView()
            else showReactRootView()
            compose = !compose
            return true
        }
        fun showComposeView() {
            composeView.visibility = VISIBLE
            reactRootView.visibility = GONE
        }

        fun showReactRootView() {
            composeView.visibility = GONE
            reactRootView.visibility = VISIBLE
        }
    }


    lateinit var customView: CustomView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            koinApplication {
                loadKoinModules(module {
                    single<BaseActivity> { this@MainActivity }
                })
                loadKoinModules(koins() + ImageModule)
            }
        }

        MehmaanJunction.apply {
            junction(FeedPod.createJunction(this))
        }

        val feedViewModel: FeedViewModel by viewModels();

        sendAppStartEvent()
//        customView = CustomView(this, composeView = getComposeView(), reactRootView = getReactView(feedViewModel))
        setContentView(getComposeView())
    }

    override fun onBackPressed() {
        if (!customView.onBackPress())
            super.onBackPressed()
    }

    // Migrate to activity result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        reactAdapter.onPermissionResult(this, requestCode, resultCode, data)
    }
}
