@file:Suppress("UNCHECKED_CAST")

package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Property
import java.lang.reflect.Field

class FieldProperty<P : Any, T : Any>(private val field: Field) : Property<P, T> {
    override val name: String = field.name

    override val type: Class<T> = field.type as Class<T>

    override fun set(on: P, value: T): T? {
        val previous = get(on)
        field.set(on, value)
        return previous
    }

    override fun get(from: P): T? = field.get(from) as T?
    override fun toString(): String {
        return "FieldProperty(field=$field, name='$name', type=$type)"
    }
    override val owner: Class<P> = field.declaringClass as Class<P>


}
