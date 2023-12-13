package app.mehmaan.worker.workscheduler

import kotlinx.browser.window
import org.w3c.dom.Window

class WebWorkScheduler(): WorkScheduler<Window>(window) {

}

