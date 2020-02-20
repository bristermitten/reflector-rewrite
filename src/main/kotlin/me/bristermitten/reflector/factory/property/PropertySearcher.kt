package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Properties

interface PropertySearcher {
    fun <T : Any> findProperties(clazz: Class<T>): Properties<T>
}
