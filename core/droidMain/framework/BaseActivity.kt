package app.mehmaan.core.framework

import android.app.Activity
import android.view.KeyEvent
import androidx.activity.ComponentActivity

abstract class BaseActivity: ComponentActivity() {
    val adapters = mutableListOf<AndroidAdapter<*>>()

    fun connect(adapterList: List<AndroidAdapter<*>>) {
        adapters.addAll(adapterList)
        adapterList.forEach(lifecycle::addObserver)
    }

    /**
     * Prefer this function to connect your adapters
     * onDestroy removes all adapters
     * You can add adapters in multiple steps
     * And can also do eager disconnect
     */
    fun connect(vararg adapters: AndroidAdapter<*>) = connect(adapters.toList())

    fun disconnect(adapterList: List<AndroidAdapter<*>>) {
        this.adapters.removeAll(adapterList)
        adapterList.forEach(lifecycle::removeObserver)
    }

    fun disconnect(vararg adapters: AndroidAdapter<*>) = disconnect(adapters.toList())

    override fun onDestroy() {
        super.onDestroy()
        disconnect(adapters)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        adapters.forEach {
            it.onBackPressed(this)
        }
    }

    /*
    Create a KeyHandleDelegate & Interface and forward all key events there
    God observers are also not good.
    Ordering of Observers matters here,
    first to handle and return true will prevent other observers from running
    */
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        adapters.forEach {
            val result = it.onKeyUp(this, keyCode, event)
            if (result) return true
        }
        return super.onKeyUp(keyCode, event)
    }
}
