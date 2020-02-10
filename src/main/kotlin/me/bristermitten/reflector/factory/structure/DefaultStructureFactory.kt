package me.bristermitten.reflector.factory.structure

import me.bristermitten.reflector.entity.Structure
import me.bristermitten.reflector.entity.impl.ClassStructure
import me.bristermitten.reflector.factory.Cache
import me.bristermitten.reflector.factory.property.PropertySearcher

class DefaultStructureFactory(
    private val propertySearcher: PropertySearcher
) : StructureFactory {

    private val cache: Cache<Class<*>, Structure<*>> =
        Cache(this::create)

    override fun <T> createStructure(clazz: Class<T>): Structure<T> {
        @Suppress("UNCHECKED_CAST")
        return cache.get(clazz) as Structure<T>
    }

    private fun create(clazz: Class<*>): Structure<*> {
        return ClassStructure(clazz, propertySearcher.findProperties(clazz))
    }

}
