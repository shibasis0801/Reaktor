package app.mehmaan.mobile.view

import app.mehmaan.core.framework.WeakRef
import platform.UIKit.UIView
import platform.UIKit.UIViewController


class DarwinView(view: UIView): PlatformView<UIView, UIViewController> {
    override val view = WeakRef(view)
    override fun show() = invoke {
        hidden = false
    }

    override fun hide() = invoke {
        hidden = true
    }

    override fun create(controller: UIViewController) = UIView()
}
