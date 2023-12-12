package app.mehmaan.core.adapters

import app.mehmaan.core.framework.Adapter
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



// Build BinaryTransport for each platform
// from JNI expose a ByteBuffer Transport
// from jsi, expose a ByteBuffer Transport
// from flutter, expose a ByteBuffer Transport
// from objc, expose a ByteBuffer Transport
// from cpp, expose a ByteBuffer Transport

interface SyncModule {
    fun identity(data: String): String
}

interface AsyncModule {
    suspend fun identity(data: String): String
}

interface StreamingModule {
    fun identity(data: Flow<String>): Flow<String>
}

data class RemoteCommand(
    val className: String,
    val methodName: String,
    val payload: ByteArray
)
sealed interface Transport

// IPC / HTTP
interface BlockingTransport: Transport {
}

// IPC / HTTP
interface NonBlockingTransport: Transport {

}

// SSE
interface ClientStreamingTransport: Transport {

}

// WebSockets
interface DuplexStreamingTransport: Transport {

}
