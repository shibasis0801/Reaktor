@file:JvmName("ActivityUtils")
package app.mehmaan.core.extensions

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import app.mehmaan.core.framework.BaseActivity
import kotlin.math.min

fun ComponentActivity.getSimpleName() : String {
    return "FIX_LINT"
}

fun ComponentActivity.getTag() : String {
    val length = this.getSimpleName().length
    val till = min(length - 1, 20)
    return this.getSimpleName().substring(0..till)
}


fun Activity.toast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


inline fun <reified T : Activity> ComponentActivity.startActivity(disableAnimation: Boolean = false) {
    startActivity(Intent(this, T::class.java).apply {
        if (disableAnimation)
            addFlags(FLAG_ACTIVITY_NO_ANIMATION)
    })
}

inline fun <reified T : Activity> ComponentActivity.finishAndStart() {
    startActivity(Intent(this, T::class.java))
    finish()
}

fun ComponentActivity.safeIntentDispatch(intent : Intent) {
    intent.resolveActivity(packageManager)?.let {
        startActivity(intent)
    }
}

fun PackageManager.intentHandlerExists(intent : Intent) = intent.resolveActivity(this) != null

val BaseActivity.scope: LifecycleCoroutineScope
    get() = lifecycle.coroutineScope

fun Activity.hasPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
