package app.mehmaan.core.framework

import org.koin.core.component.KoinComponent

/*
A component receives events from Controllers (activity, application, viewcontroller, window, etc)
It is sealed so that you don't subclass it (visibility modifiers weren't working)
You should always inherit Component instead of ComponentContract
*/
expect sealed interface ComponentContract: KoinComponent {
    fun handle(event: ControllerEvent)
}

interface Component: ComponentContract {
    override fun handle(event: ControllerEvent) {}
}