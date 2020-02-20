package me.bristermitten.reflector.entity

interface Property<P : Any, T : Any> : Element<T> {

    /**
     * Set the value of the property on a given object
     * Since it is not known at compile time if a property can be set (i.e if it is final or not),
     * this will fail gracefully if not, simply doing nothing.
     *
     * * @param on the object to set the property on
     * @param value the value to set the property to
     * @return the value of the property before setting, if any.
     */
    operator fun set(on: P, value: T): T?

    operator fun get(from: P): T?

    val owner: Class<P>
}
