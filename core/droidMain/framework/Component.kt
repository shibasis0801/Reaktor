package app.mehmaan.core.framework

import androidx.lifecycle.DefaultLifecycleObserver
import org.koin.core.component.KoinComponent

/*
Components are standalone lifecycle based plugins (Should have no reference to activity)
Adapters are connections between Components and Activity framework (Should have a reference to activity)
(since we don't have a global window like web)
(This is a SingleActivity Application)
 */
interface AndroidComponent: Component, DefaultLifecycleObserver {}
