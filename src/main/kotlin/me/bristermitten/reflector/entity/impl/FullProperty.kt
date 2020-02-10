@file:Suppress("UNCHECKED_CAST")

package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Property
import java.lang.reflect.Field
import java.lang.reflect.Method

class FullProperty<T>(private val field: Field, private val getter: Method, private val setter: Method) : Property<T> {
    override val name: String = field.name

    override val type: Class<T> = field.type as Class<T>

    override fun set(value: T, on: Any): T? {
        val previous = get(on)
        val returned = setter.invoke(on, value)
        //TODO use returned?
        return previous
    }

    override fun get(from: Any): T? = getter.invoke(from) as T?

    override fun toString(): String {
        return "FullProperty(field=$field, getter=$getter, setter=$setter, name='$name', type=$type)"
    }
}
