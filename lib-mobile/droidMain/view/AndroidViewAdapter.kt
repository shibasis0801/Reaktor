package app.mehmaan.mobile.view.view

import androidx.compose.ui.platform.ComposeView
import app.mehmaan.core.framework.BaseActivity
import app.mehmaan.core.framework.WeakRef
import app.mehmaan.mobile.compose.MehmaanTheme
import app.mehmaan.mobile.view.ViewAdapter
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext

abstract class AndroidViewAdapter(
    activity: BaseActivity
): SimpleViewManager<ComposeView>(), ViewAdapter<BaseActivity> {
    override val ref = WeakRef(activity)
    override fun getName() = this::class.simpleName!!
    override fun createViewInstance(reactContext: ThemedReactContext) = invoke {
        ComposeView(this).apply {
            setContent {
                MehmaanTheme {
                    Render()
                }
            }
        }
    } ?: throw NullPointerException("Activity Not Valid")
}