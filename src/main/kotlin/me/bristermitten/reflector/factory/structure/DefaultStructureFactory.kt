package me.bristermitten.reflector.factory.structure

import me.bristermitten.reflector.entity.Structure
import me.bristermitten.reflector.entity.impl.ClassStructure
import me.bristermitten.reflector.factory.Cache
import me.bristermitten.reflector.factory.property.PropertySearcher

class DefaultStructureFactory(
    private val propertySearcher: PropertySearcher
) : StructureFactory {

    private val cache: Cache<Class<*>, Structure<*>> = Cache(this::create)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> createStructure(clazz: Class<T>): Structure<T> {
        return cache[clazz] as Structure<T>
    }

    private fun create(clazz: Class<*>): Structure<*> {
        @Suppress("UNCHECKED_CAST")
        return ClassStructure(clazz as Class<Any>, propertySearcher.findProperties(clazz))
    }

}
