package me.bristermitten.reflector.entity

import kotlin.reflect.KProperty1

interface PropertySet : Set<Property<*>> {

    operator fun <T> get(field: KProperty1)
}
