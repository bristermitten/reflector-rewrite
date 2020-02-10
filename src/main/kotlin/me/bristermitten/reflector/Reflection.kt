package me.bristermitten.reflector

import me.bristermitten.reflector.config.NamingStrategy
import java.lang.reflect.Field
import java.lang.reflect.Method

object Reflection {


    fun hasGetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Boolean {
        return getGetter(field, namingStrategy) != null
    }

    fun getGetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Method? {
        val getterName = namingStrategy.getterName(field.name, field.type)
        return try {
            field.declaringClass.getMethod(getterName) ?: field.declaringClass.getDeclaredMethod(getterName)
        } catch (e: NoSuchMethodException) {
            null
        }
    }

    fun hasSetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Boolean {
        return getSetter(field, namingStrategy) != null
    }

    fun getSetter(field: Field, namingStrategy: NamingStrategy = NamingStrategy.strategy): Method? {
        val setterName = namingStrategy.setterName(field.name, field.type)
        return try {
            field.declaringClass.getMethod(setterName) ?: field.declaringClass.getDeclaredMethod(setterName)
        } catch (e: NoSuchMethodException) {
            null
        }
    }
}
