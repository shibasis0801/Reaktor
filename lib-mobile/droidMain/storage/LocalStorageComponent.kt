package storage

import android.graphics.Bitmap
import app.mehmaan.core.adapters.StorageAdapter
import concurrency.Dispatch
import java.io.File

class LocalStorageComponent(
    storageAdapter: StorageAdapter
): StorageComponent(storageAdapter) {

    suspend fun createFileFrom(
        bitmap: Bitmap
    ) = Dispatch.inBackground {
        val screenshotFile = File(
            storageInteractor.getHomeDirectory(),
            storageInteractor.timeStampedFileName("png")
        )
        screenshotFile.apply {
            outputStream().use {
                bitmap.compress(Bitmap.CompressFormat.WEBP, 70/* ignored for PNG*/, it)
                it.flush()
            }
        }
    }.await()
}