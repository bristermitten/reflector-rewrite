package me.bristermitten.reflector

import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.Structure
import me.bristermitten.reflector.factory.property.PropertySearcher
import me.bristermitten.reflector.factory.structure.StructureFactory
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

/**
 * Main Class of Reflector.
 * This class is responsible for delegating basic Structure and Property lookup classes to their corresponding factories.
 * Functionality is customisable through implementing the factory classes, and using [ReflectorBuilder]
 *
 * This should ideally be kept a singleton, as all caches are class-level, not static.
 *
 */
class Reflector(
    private val structureFactory: StructureFactory,
    private val propertySearcher: PropertySearcher
) {

    /**
     * Create or get a [Structure] of a given class.
     * @param clazz the class
     * @return a structure corresponding to the class.
     */
    fun <T : Any> structureOf(clazz: Class<T>): Structure<T> = structureFactory.createStructure(clazz)


    /**
     * Get or create a property from a given class, that matches a given name directly.
     * This is nullable, as the named property may not exist.
     * @param clazz the class holding the property
     * @param name the property name
     * @return A property matching the given name, or null if no property exists
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any, P : Any> getProperty(clazz: Class<T>, name: String): Property<T, P>? {
        return propertySearcher.findProperties(clazz)[name]
    }

    /*
     * Kotlin Support
     */

    /**
     * Reified wrapper of [structureOf]
     * @see Reflector.structureOf
     */
    inline fun <reified T : Any> structureOf(): Structure<T> = structureOf(T::class.java)

    /**
     * Reified wrapper of [getProperty]
     * @see Reflector.getProperty
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any, P : Any> getProperty(name: String): Property<T, P>? {
        return getProperty(T::class.java, name)
    }

    /**
     * Reified wrapper of [getProperty]
     * However, unlike the default, this is non-null as if the property exists at compile time then it will always exist at runtime.
     * This property will not have setting functionality as it is not mutable.
     * @see Reflector.getProperty
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any, P : Any> getProperty(property: KProperty1<T, P>): Property<T, P> {
        return getProperty(T::class.java, property.name)!!
    }

    /**
     * Reified wrapper of [getProperty]
     * However, unlike the default, this is non-null as if the property exists at compile time then it will always exist at runtime.
     * @see Reflector.getProperty
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any, P : Any> getProperty(property: KMutableProperty1<T, P>): Property<T, P> {
        return getProperty(T::class.java, property.name)!!
    }

    companion object {
        /**
         * Helper for making a new [Reflector] with default settings
         */
        fun create() = ReflectorBuilder().build()
    }
}
