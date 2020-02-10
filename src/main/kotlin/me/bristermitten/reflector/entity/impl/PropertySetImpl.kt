package me.bristermitten.reflector.entity.impl

import me.bristermitten.reflector.entity.Property
import me.bristermitten.reflector.entity.PropertySet

class PropertySetImpl(private val set: Set<Property<*>>) : PropertySet {
    override val size: Int = set.size

    override fun contains(element: Property<*>) = set.contains(element)

    override fun containsAll(elements: Collection<Property<*>>) = set.containsAll(elements)

    override fun isEmpty() = set.isEmpty()

    override fun iterator(): Iterator<Property<*>> = set.iterator()

    override fun toString() = set.toString()
}
