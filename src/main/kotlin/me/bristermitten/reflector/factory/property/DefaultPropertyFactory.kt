package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.impl.FieldAndGetterProperty
import me.bristermitten.reflector.entity.impl.FieldProperty
import me.bristermitten.reflector.entity.impl.FullProperty
import java.lang.reflect.Field
import java.lang.reflect.Method

class DefaultPropertyFactory : PropertyFactory {

    override fun <T : Any> createProperty(field: Field): Property<Any, T> {
        return FieldProperty(field)
    }

    override fun <T : Any> createProperty(field: Field, getter: Method): Property<Any, T> {
        return FieldAndGetterProperty(field, getter)
    }

    override fun <T : Any> createProperty(field: Field, getter: Method, setter: Method): Property<Any,T> {
        return FullProperty(field, getter, setter)
    }
}
