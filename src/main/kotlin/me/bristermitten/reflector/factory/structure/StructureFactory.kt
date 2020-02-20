package me.bristermitten.reflector.factory.structure

import me.bristermitten.reflector.entity.Structure

interface StructureFactory {
    fun <T : Any> createStructure(clazz: Class<T>): Structure<T>
}
