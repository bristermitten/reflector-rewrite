@file:Suppress("MemberVisibilityCanBePrivate")

package me.bristermitten.reflector

import me.bristermitten.reflector.factory.property.DefaultPropertyFactory
import me.bristermitten.reflector.factory.property.DefaultPropertySearcher
import me.bristermitten.reflector.factory.property.PropertyFactory
import me.bristermitten.reflector.factory.property.PropertySearcher
import me.bristermitten.reflector.factory.structure.DefaultStructureFactory
import me.bristermitten.reflector.factory.structure.StructureFactory

class ReflectorBuilder {
    var propertyFactory: PropertyFactory = DefaultPropertyFactory()
    var propertySearcher: PropertySearcher = DefaultPropertySearcher(propertyFactory)
    var structureFactory: StructureFactory = DefaultStructureFactory(propertySearcher)

    fun build(): Reflector {
        return Reflector(structureFactory)
    }
}
