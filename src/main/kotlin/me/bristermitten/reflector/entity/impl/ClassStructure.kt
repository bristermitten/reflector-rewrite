package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Properties
import me.bristermitten.reflector.entity.Structure

class ClassStructure<T : Any>(
    override val type: Class<T>,
    override val properties: Properties<T>
) : Structure<T> {

    override val name: String = type.name

}
