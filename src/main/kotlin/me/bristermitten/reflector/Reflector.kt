package me.bristermitten.reflector

import me.bristermitten.reflector.entity.Structure
import me.bristermitten.reflector.factory.structure.StructureFactory

/**
 * Main Class of Reflector.
 * This should ideally be a singleton, as all caches are class-level, not static
 */
class Reflector(
    private val structureFactory: StructureFactory
) {

    fun <T> structureOf(clazz: Class<T>): Structure<T> = structureFactory.createStructure(clazz)

    inline fun <reified T> structureOf(): Structure<T> = structureOf(T::class.java)

    companion object {
        fun create() = ReflectorBuilder().build()
    }
}
