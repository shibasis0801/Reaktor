package app.mehmaan.mobile.view

import android.app.Activity
import android.view.View
import app.mehmaan.core.framework.WeakRef

class AndroidView(view: View): PlatformView<View, Activity> {
    override val view = WeakRef(view)
    override fun show() = invoke {
        visibility = View.VISIBLE
    }

    override fun hide() = invoke {
        visibility = View.GONE
    }

    override fun create(controller: Activity) = View(controller)
}
