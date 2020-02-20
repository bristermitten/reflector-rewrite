package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Property
import java.lang.reflect.Field
import java.lang.reflect.Method

interface PropertyFactory {
    fun <T : Any> createProperty(field: Field): Property<Any, T>
    fun <T : Any> createProperty(field: Field, getter: Method): Property<Any, T>
    fun <T : Any> createProperty(field: Field, getter: Method, setter: Method): Property<Any, T>
}
