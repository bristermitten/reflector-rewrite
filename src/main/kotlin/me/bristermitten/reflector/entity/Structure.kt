package me.bristermitten.reflector.entity

/**
 * A structure of a class
 */
interface Structure<T> : Element<T> {

    val properties: PropertySet


}
