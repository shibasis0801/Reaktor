package app.mehmaan.core.adapters

import app.mehmaan.core.framework.Adapter

abstract class StorageAdapter<Controller>(
    controller: Controller
): Adapter<Controller>(controller) {
    abstract fun test(): Int
//    fun getHomeDirectory(): String = ""
}
