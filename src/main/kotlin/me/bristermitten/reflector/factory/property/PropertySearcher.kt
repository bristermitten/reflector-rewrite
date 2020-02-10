package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.PropertySet

interface PropertySearcher {
    fun findProperties(clazz: Class<*>): PropertySet
}
