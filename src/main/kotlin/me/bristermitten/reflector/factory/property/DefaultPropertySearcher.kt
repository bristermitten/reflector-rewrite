@file:Suppress("UNCHECKED_CAST")

package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.Properties
import me.bristermitten.reflector.entity.impl.PropertiesImpl
import me.bristermitten.reflector.factory.Cache
import me.bristermitten.reflector.getGetter
import me.bristermitten.reflector.getSetter
import java.lang.reflect.Field

class DefaultPropertySearcher(private val propertyFactory: PropertyFactory) : PropertySearcher {

    private val cache: Cache<Class<*>, Properties<*>> = Cache(::find)

    override fun <T : Any> findProperties(clazz: Class<T>): Properties<T> = cache.get(clazz) as Properties<T>

    private fun find(clazz: Class<*>): Properties<*> {
        val properties = (clazz.fields + clazz.declaredFields)
            .distinct()
            .filterNotNull()
            .map {it.name to  this.createProperty(it) }
            .toMap()


        return PropertiesImpl(properties)
    }

    private fun createProperty(field: Field): Property<Any, Any> {
        val getter = getGetter(field)
        val setter = getSetter(field)

        field.isAccessible = true
        getter?.isAccessible = true
        setter?.isAccessible = true

        val property: Property<Any, Any>
        property = when {
            getter != null && setter != null -> {
                propertyFactory.createProperty(field, getter, setter)
            }
            getter != null -> {
                propertyFactory.createProperty(field, getter)
            }
            else -> propertyFactory.createProperty(field)
        }
        return property
    }
}
