package me.bristermitten.reflector.factory.structure

import me.bristermitten.reflector.entity.Structure

interface StructureFactory {
    fun <T> createStructure(clazz: Class<T>): Structure<T>
}
