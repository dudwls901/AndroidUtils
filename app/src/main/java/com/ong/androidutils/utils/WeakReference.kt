package com.ong.androidutils.utils

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate WeakReference
 *
 * This function make the use of weak reference easier with 'by' delegation.
 * 'Weak Reference Object' is strongly reachable, 'Referent' referenced
 * by reference object is weakly reachable that is always removed when gc occurs.
 * It can be used to store temporary objects such as LRU Caching, Image Caching,
 * and referencing activities inside the handler.
 * */
fun <T> weakReference(tIn: T? = null): ReadWriteProperty<Any?, T?> {
    return object : ReadWriteProperty<Any?, T?> {
        var t = WeakReference<T?>(tIn)
        override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            return t.get()
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            t = WeakReference(value)
        }
    }
}
