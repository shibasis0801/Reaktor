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

    private fun convert(permissions: Array<out String>): Array<String> {
        return permissions.map {
            when(it) {
                Permission.CAMERA -> Manifest.permission.CAMERA
                else -> it
            }
        }.toTypedArray()
    }

    override suspend fun request(vararg permissions: String): Boolean {
        val activity = ref.get() ?: return false
        val actual = convert(permissions)
        if (actual.all { activity.hasPermission(it) }) {
            return true
        }
        val result = activity.getResultFromActivity(ActivityResultContracts.RequestMultiplePermissions(), actual)
        return result.all { it.value }
    }
}
