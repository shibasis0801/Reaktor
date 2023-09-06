package app.mehmaan.mobile.view

import app.mehmaan.core.framework.WeakRef
import web.dom.Document
import web.html.HTMLElement


class WebView(view: HTMLElement): PlatformView<HTMLElement, Document> {
    override val view = WeakRef(view)
    override fun show() = invoke {
        hidden = false
    }

    override fun hide() = invoke {
        hidden = true
    }

    override fun create(controller: Document) = controller.createElement("div")
}
