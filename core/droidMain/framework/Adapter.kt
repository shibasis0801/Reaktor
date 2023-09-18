package app.mehmaan.core.framework

import android.app.Activity
import android.view.KeyEvent
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import app.mehmaan.core.framework.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import java.lang.ref.WeakReference

abstract class AndroidAdapter<ConcreteAdapter: AndroidAdapter<ConcreteAdapter>>(
    activity: BaseActivity
): Adapter<BaseActivity, ConcreteAdapter>(activity), AndroidComponent {
    override val ref = WeakRef(activity)
    val activity
        get() = ref.get()

    val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(owner: LifecycleOwner) { invoke { onCreate(this) } }
    override fun onStart(owner: LifecycleOwner) { invoke { onStart(this) } }
    override fun onResume(owner: LifecycleOwner) { invoke { onResume(this) } }
    override fun onPause(owner: LifecycleOwner) { invoke { onPause(this) } }
    override fun onStop(owner: LifecycleOwner) { invoke { onStop(this) } }
    override fun onDestroy(owner: LifecycleOwner) { invoke { onDestroy(this) } }

    open fun onCreate(activity: BaseActivity) {}
    open fun onStart(activity: BaseActivity) {}
    open fun onResume(activity: BaseActivity) {}
    open fun onPause(activity: BaseActivity) {}
    open fun onStop(activity: BaseActivity) {}
    open fun onDestroy(activity: BaseActivity) {}

    // New Observers
    open fun onBackPressed(activity: BaseActivity) {}
    // Order matters
    open fun onKeyUp(activity: BaseActivity, keyCode: Int, event: KeyEvent?): Boolean = false
}
