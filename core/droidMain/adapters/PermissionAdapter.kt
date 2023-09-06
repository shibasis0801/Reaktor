package app.mehmaan.core.adapters

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import app.mehmaan.core.extensions.hasPermission
import app.mehmaan.core.framework.AndroidAdapter
import app.mehmaan.core.framework.BaseActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.resume

class PermissionAdapter(activity: BaseActivity): AndroidAdapter<PermissionAdapter>(activity) {
    private var requestId = AtomicInteger(0)
    private fun nextId() = "Permission#${requestId.getAndIncrement()}"

    private val launchers = arrayListOf<ActivityResultLauncher<*>>()

    suspend fun request(vararg permissions: String) = invokeSuspend {
        suspendCancellableCoroutine { continuation ->
            if (permissions.all(::hasPermission)) {
                continuation.resume(true)
                return@suspendCancellableCoroutine
            }

            val launcher = activityResultRegistry.register(nextId(), ActivityResultContracts.RequestMultiplePermissions()) {
                val allGranted = it.all { it.value }
                if (allGranted) {
                    continuation.resume(true)
                } else {
                    continuation.resume(false)
                }
            }
            launcher.launch(arrayOf(*permissions))
            launchers.add(launcher)
        }
    } ?: false

    override fun onDestroy(activity: BaseActivity) {
        launchers.forEach {
            it.unregister()
        }
        launchers.clear()
    }
}
