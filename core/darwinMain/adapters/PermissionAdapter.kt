package app.mehmaan.core.adapters

import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.UIKit.UIViewController


typealias PermissionRequestHandler = suspend () -> PermissionResult

class DarwinPermissionAdapter(controller: UIViewController) {
    private val requestHandler = HashMap<String, PermissionRequestHandler>()
    fun addHandler(permission: String, handler: PermissionRequestHandler) {
        requestHandler[permission] = handler
    }
    suspend fun request(vararg permissions: String): Boolean {
        var granted = true
        for (permission in permissions) {
            val result = requestHandler[permission]?.invoke()
            granted = granted && result == PermissionResult.Granted
        }
        return granted
    }
}

fun t() {
    val x = NSCachesDirectory
}