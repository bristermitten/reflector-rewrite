package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Properties
import me.bristermitten.reflector.entity.Property
import kotlin.reflect.KProperty1

class PropertiesImpl<P : Any>(private val map: Map<String, Property<P, Any>>) : Properties<P>,
    Collection<Property<P, Any>> by map.values {

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(field: KProperty1<P, T>): Property<P, T> {
        return get(field.name)!!
    }

    override fun toString() = map.toString()


    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(name: String) = map[name] as? Property<P, T>

}
