package me.bristermitten.reflector.entity.info

import java.lang.reflect.Modifier

interface Info {
    fun hasAnnotation(annotation: Class<out Annotation>): Boolean
    fun modifiers() : Set<Modifier>
}
