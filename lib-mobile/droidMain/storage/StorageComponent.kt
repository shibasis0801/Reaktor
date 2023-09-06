package storage

import app.mehmaan.core.framework.AndroidComponent
import app.mehmaan.core.adapters.StorageAdapter

abstract class StorageComponent(
    val storageInteractor: StorageAdapter
): AndroidComponent {

}
