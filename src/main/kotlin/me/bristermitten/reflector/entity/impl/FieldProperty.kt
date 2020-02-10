@file:Suppress("UNCHECKED_CAST")

package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Property
import java.lang.reflect.Field

class FieldProperty<T>(private val field: Field) : Property<T> {
    override val name: String = field.name

    override val type: Class<T> = field.type as Class<T>

    override fun set(value: T, on: Any): T? {
        val previous = get(on)
        field.set(on, value)
        return previous
    }

    override fun get(from: Any): T? = field.get(from) as T?
    override fun toString(): String {
        return "FieldProperty(field=$field, name='$name', type=$type)"
    }


}
