package me.bristermitten.reflector.entity

/**
 * A structure of a class
 */
interface Structure<T : Any> : Element<T> {

    val properties: Properties<T>


}
