package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.PropertySet
import me.bristermitten.reflector.entity.Structure

class ClassStructure<T>(
    override val type: Class<T>,
    override val properties: PropertySet
) : Structure<T> {

    override val name: String = type.name

}
