package app.mehmaan.core.adapters

import app.mehmaan.core.framework.Adapter
import dev.shibasis.reaktor.framework.Feature
import kotlinx.coroutines.flow.Flow


sealed class PermissionResult {
    data object Granted: PermissionResult()
    sealed class Denied(): PermissionResult() {
        data class Unknown(val error: Error): Denied()
        data object Once: Denied()
        data object Forever: Denied()
    }
}

object Permission {
    val CAMERA = "CAMERA"
    val LOCATION = "LOCATION"
    val STORAGE = "STORAGE"
}

abstract class PermissionAdapter<Controller>(controller: Controller): Adapter<Controller>(controller) {
    // requestAll is binary yes or no for all permissions
    abstract suspend fun request(vararg permissions: String): Boolean

    // You can also override this method for more granular permission handling
    suspend fun requestOptional(vararg permissions: String): Map<String, PermissionResult> = hashMapOf()
}


private val permissionId = Feature.createId()
var Feature.Permission: PermissionAdapter<*>?
    get() = fetchModule(permissionId)
    set(permissionAdapter) = storeModule(permissionId, permissionAdapter)