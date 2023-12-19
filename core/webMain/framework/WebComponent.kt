package app.mehmaan.core.framework

actual sealed interface ControllerEventObserver {
    actual fun handle(event: ControllerEvent)
}
