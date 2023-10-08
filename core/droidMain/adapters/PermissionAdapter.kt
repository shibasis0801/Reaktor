package app.mehmaan.core.adapters

import android.Manifest
import android.app.Application
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import app.mehmaan.core.extensions.getResultFromActivity
import app.mehmaan.core.extensions.hasPermission
import app.mehmaan.core.framework.Adapter
import app.mehmaan.core.framework.BaseActivity
import app.mehmaan.core.framework.WeakRef
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.resume

class AndroidPermissionAdapter(
    activity: ComponentActivity
): PermissionAdapter<ComponentActivity>(activity) {

    fun check(permission: String) = invoke {
        val actualPermissions = when(permission) {
            Permission.CAMERA -> listOf(Manifest.permission.CAMERA)
            else -> listOf(permission)
        }

        actualPermissions.all { hasPermission(it) }
    } ?: false

    override suspend fun request(vararg permissions: String): Boolean {
        val activity = ref.get() ?: return false
        if (permissions.all(::check)) {
            return true
        }
        val result = activity.getResultFromActivity(ActivityResultContracts.RequestMultiplePermissions(), arrayOf(*permissions))
        return result.all { it.value }
    }
}
