package app.mehmaan.mobile.view

import app.mehmaan.core.framework.WeakRef
import app.mehmaan.mobile.view.ViewAdapter
import platform.UIKit.UIViewController

abstract class DarwinViewAdapter(
    uiViewController: UIViewController
): ViewAdapter<UIViewController> {
    override val ref = WeakRef(uiViewController)

}