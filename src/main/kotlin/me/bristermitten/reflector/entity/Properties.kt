package me.bristermitten.reflector.entity

import kotlin.reflect.KProperty1

@Suppress("UNCHECKED_CAST")
interface Properties<P : Any> : Collection<Property<P, Any>> {

    operator fun <T : Any> get(field: KProperty1<P, T>): Property<P, T>
    operator fun <T : Any> get(name: String): Property<P, T>?

    fun <T : Any> byType(type: Class<T>): Collection<Property<P, T>> {
        return filter {
            it.type == type
        }.map { it as Property<P, T> }
    }


    operator fun <T : Any> get(property: Property<*, T>): Property<P, T>? {
        val byName: Property<P, T> = this[property.name] ?: return null
        return if (byName.type == property.type) byName else null
    }
}
