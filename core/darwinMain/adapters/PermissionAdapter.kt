package app.mehmaan.core.adapters

import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVMediaType
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.requestAccessForMediaType
import platform.Foundation.NSCachesDirectory
import kotlin.coroutines.resume


typealias PermissionRequestHandler = suspend () -> PermissionResult

class DarwinPermissionAdapter(): PermissionAdapter<Unit>(Unit) {
    private val requestHandler = HashMap<String, PermissionRequestHandler>()

    init {
        addHandler(Permission.CAMERA, ::cameraPermissionHandler)
    }

    fun addHandler(permission: String, handler: PermissionRequestHandler) {
        requestHandler[permission] = handler
    }
    override suspend fun request(vararg permissions: String): Boolean {
        var granted = true
        for (permission in permissions) {
            val result = requestHandler[permission]?.invoke()
            granted = granted && result == PermissionResult.Granted
        }
        return granted
    }
}

suspend fun cameraPermissionHandler() = suspendCancellableCoroutine { continuation ->
    AVCaptureDevice.requestAccessForMediaType(AVMediaTypeVideo) {
        if (it) {
            continuation.resume(PermissionResult.Granted)
        }
        else {
            continuation.resume(PermissionResult.Denied.Once)
        }
    }
}

fun t() {
    val x = NSCachesDirectory
}


interface SyncModule {
    fun identity(data: String): String
}

interface AsyncModule {
    suspend fun identity(data: String): String
}

interface StreamingModule {

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



