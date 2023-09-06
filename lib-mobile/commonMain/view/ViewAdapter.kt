package app.mehmaan.mobile.view

import androidx.compose.runtime.Composable
import app.mehmaan.core.framework.AdapterContract

interface ViewAdapter<Controller: Any>: AdapterContract<Controller, ViewAdapter<Controller>> {
    @Composable
    abstract fun render()
}
