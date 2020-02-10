package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Property
import java.lang.reflect.Field
import java.lang.reflect.Method

interface PropertyFactory {
    fun <T> createProperty(field: Field): Property<T>
    fun <T> createProperty(field: Field, getter: Method): Property<T>
    fun <T> createProperty(field: Field, getter: Method, setter: Method): Property<T>
}
