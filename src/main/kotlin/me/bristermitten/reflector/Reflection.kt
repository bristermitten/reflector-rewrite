/**
 * Provides functions to wrap Java's verbose reflection.
 * Note that this is stateless, and does not cache any values
 */
package me.bristermitten.reflector

import me.bristermitten.reflector.config.NamingStrategy
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Get a getter method for a given field, if one exists, otherwise null
 * This respects the provided [NamingStrategy]
 * @param field the field to find a getter for
 * @param namingStrategy a naming strategy for creating getter names
 * @return a getter method if present, otherwise null
 */
fun getGetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Method? {
    val getterName = namingStrategy.getterName(field.name, field.type)
    return try {
        field.declaringClass.getMethod(getterName) ?: field.declaringClass.getDeclaredMethod(getterName)
    } catch (e: NoSuchMethodException) {
        null
    }
}

/**
 * Get a setter method for a given field, if one exists, otherwise null
 * This respects the provided [NamingStrategy]
 * @param field the field to find a setter for
 * @param namingStrategy a naming strategy for creating setter names
 * @return a setter method if present, otherwise null
 */
fun getSetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Method? {
    val setterName = namingStrategy.setterName(field.name, field.type)
    return try {
        field.declaringClass.getMethod(setterName, field.type) ?: field.declaringClass.getDeclaredMethod(setterName, field.type)
    } catch (e: NoSuchMethodException) {
        null
    }
}
