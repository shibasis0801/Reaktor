package app.mehmaan.core.adapters

import androidx.activity.ComponentActivity
import app.mehmaan.core.framework.Adapter
import app.mehmaan.core.framework.WeakRef
import java.io.File
import java.util.*


class FileAdapter(activity: ComponentActivity): Adapter<ComponentActivity>(activity) {
    override val ref = WeakRef(activity)
    fun createTempFile() = invoke {
        File.createTempFile("sound_${Date().time}.3gp", null, cacheDir)
    }
}
