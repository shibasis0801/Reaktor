package app.mehmaan.mobile.view

import app.mehmaan.core.framework.WeakRef

// Also build the same thing for a controller (activity, uiviewcontroller, window)

interface PlatformView<T: Any, Controller> {
    val view: WeakRef<T>
    operator fun<Result> invoke(function: T.() -> Result) = view.get()?.run(function) ?: throw NullPointerException("View is null")
    fun show()
    fun hide()
    fun create(controller: Controller): T
}

interface IntersectionObserver {

}
