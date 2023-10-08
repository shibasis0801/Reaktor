package app.mehmaan.core.framework

import org.koin.core.component.KoinComponent

actual sealed interface ComponentContract: KoinComponent {
    actual fun handle(event: ControllerEvent)
}
