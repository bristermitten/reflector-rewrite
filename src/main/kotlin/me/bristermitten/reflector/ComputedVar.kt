package me.bristermitten.reflector

import kotlin.reflect.KProperty

/**
 * A delegate that will compute the property value from a given function if it is null,
 * but otherwise will use a set value
 */
class ComputedVar<T>(private val compute: () -> T) {
    private var value: T? = null
    operator fun getValue(reflectorBuilder: ReflectorBuilder, property: KProperty<*>): T {
        if (value == null)
            value = compute()
        return value!!
    }

    operator fun setValue(reflectorBuilder: ReflectorBuilder, property: KProperty<*>, s: T) {
        value = s
    }
}

fun <T> computedByDefault(compute: () -> T) = ComputedVar(compute)
