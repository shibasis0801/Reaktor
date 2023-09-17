package view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import com.facebook.react.ReactRootView

class ViewSwitcher @JvmOverloads constructor(
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
