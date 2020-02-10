package me.bristermitten.reflector.factory.property

import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.impl.FieldAndGetterProperty
import me.bristermitten.reflector.entity.impl.FieldProperty
import me.bristermitten.reflector.entity.impl.FullProperty
import java.lang.reflect.Field
import java.lang.reflect.Method

class DefaultPropertyFactory : PropertyFactory {

    override fun <T> createProperty(field: Field): Property<T> {
        return FieldProperty(field)
    }

    override fun <T> createProperty(field: Field, getter: Method): Property<T> {
        return FieldAndGetterProperty(field, getter)
    }

    override fun <T> createProperty(field: Field, getter: Method, setter: Method): Property<T> {
        return FullProperty(field, getter, setter)
    }
}
