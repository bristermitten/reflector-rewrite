package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.Reflection
import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.PropertySet
import me.bristermitten.reflector.entity.impl.PropertySetImpl
import me.bristermitten.reflector.factory.Cache
import java.lang.reflect.Field
import java.util.*
import java.util.stream.Collectors

class DefaultPropertySearcher(private val propertyFactory: PropertyFactory) : PropertySearcher {

    private val cache: Cache<Class<*>, PropertySet> = Cache(this::find)

    override fun findProperties(clazz: Class<*>): PropertySet = cache.get(clazz)

    private fun find(clazz: Class<*>): PropertySet {
        val fields = (clazz.fields + clazz.declaredFields)
            .distinct()
            .filter(Objects::nonNull)


        val properties =
            if (fields.size > PARALLEL_THRESHOLD) fields.parallelStream().map(this::createProperty)
                .collect(Collectors.toSet())
            else fields.map(this::createProperty).toSet()

        return PropertySetImpl(properties)
    }

    private fun createProperty(field: Field): Property<Any> {
        val getter = Reflection.getGetter(field)
        val setter = Reflection.getSetter(field)
        val property: Property<Any>
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

    companion object {
        const val PARALLEL_THRESHOLD = 10
    }
}
