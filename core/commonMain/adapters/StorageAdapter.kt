package app.mehmaan.core.adapters

import app.mehmaan.core.framework.Adapter
import dev.shibasis.reaktor.framework.Feature

abstract class StorageAdapter<Controller>(
    controller: Controller
): Adapter<Controller>(controller) {
    abstract fun test(): Int
//    fun getHomeDirectory(): String = ""
}

private val storageId = Feature.createId()
var Feature.Storage: StorageAdapter<*>?
    get() = fetchModule(storageId)
    set(storageAdapter) = storeModule(storageId, storageAdapter)