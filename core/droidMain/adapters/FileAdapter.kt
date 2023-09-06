package app.mehmaan.core.adapters

import app.mehmaan.core.framework.AndroidAdapter
import app.mehmaan.core.framework.BaseActivity
import java.io.File
import java.util.*


class FileAdapter(activity: BaseActivity): AndroidAdapter<FileAdapter>(activity) {
    fun createTempFile() = invoke {
        File.createTempFile("sound_${Date().time}.3gp", null, cacheDir)
    }

}
