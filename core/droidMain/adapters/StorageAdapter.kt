package app.mehmaan.core.adapters

import android.app.Activity
import app.mehmaan.core.framework.Adapter
import app.mehmaan.core.framework.AndroidAdapter
import app.mehmaan.core.framework.BaseActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Android Storage APIs are very complicated now, create abstraction using this.
 */
class StorageAdapter(activity: BaseActivity): AndroidAdapter(activity) {
    private val FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    // Android/media/package_name/directory
    fun getHomeDirectory(directory: String = "Manna"): File? {
        return invoke {
            val mediaDir = externalMediaDirs
                .firstOrNull()
                ?.let { File(it, directory) }
            if (mediaDir != null && !mediaDir.exists()) {
                    mediaDir.mkdirs()
            }

            mediaDir ?: filesDir
        }
    }

    // Send "jpg", "png", etc without the dot.
    fun timeStampedFileName(extension: String): String {
        val timestamp = SimpleDateFormat(FORMAT, Locale.UK).format(System.currentTimeMillis())
        return "$timestamp.$extension"
    }
}
