package me.bristermitten.reflector.entity

interface Property<T> : Element<T> {

    /**
     * Set the value of the property on a given object
     * Since it is not known at compile time if a property can be set (i.e if it is final or not),
     * this will fail gracefully if not, simply doing nothing.
     *
     * @param value the value to set the property to
     * @param on the object to set the property on
     * @return the value of the property before setting, if any.
     */
    fun set(value: T, on: Any): T?

    fun get(from: Any): T?
}
