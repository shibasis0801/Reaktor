package framework

import org.koin.mp.KoinPlatformTools.synchronized
import kotlin.reflect.KProperty

class onceInit<T> {
    private var value: T? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, input: T?) {
        if (value == null) {
            value = input
        }
        else {
            println("Once initialized, can't be changed")
        }
    }
}