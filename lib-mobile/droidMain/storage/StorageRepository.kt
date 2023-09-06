package app.mehmaan.core.repositories

import android.graphics.Bitmap
import java.io.File
import app.mehmaan.core.repositories.StoragePermission.*
import dev.nixlord.cloud.storage.DriveStorageComponent
import dev.nixlord.cloud.storage.FirebaseStorageComponent

sealed class StoragePermission {
    object Public: StoragePermission()
    object Shared: StoragePermission()
    object Private: StoragePermission()
    object Paid: StoragePermission()
}

class StorageRepository(
    private val localStorageComponent: storage.LocalStorageComponent,
    private val firebaseStorageComponent: FirebaseStorageComponent,
    private val driveStorageComponent: DriveStorageComponent
) {

    fun getRemoteStorage(storagePermission: StoragePermission) =
        when (storagePermission) {
            Public, Paid -> { firebaseStorageComponent }
            Shared -> { driveStorageComponent }
            Private -> { driveStorageComponent }
        }

    suspend fun storeBitmap(
        bitmap: Bitmap,
        storagePermission: StoragePermission = Private
    ): File {
        val file = localStorageComponent.createFileFrom(bitmap)
        val remoteComponent = getRemoteStorage(storagePermission)
        remoteComponent.uploadFile(file.name, file)
        return file
    }

    suspend fun getImage() {
        /*
        Given imageID, check if present in local
        If not fetch from Drive / FirebaseStorage
        Then store it in local
        Then return it
         */
    }
}
