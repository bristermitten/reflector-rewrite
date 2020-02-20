@file:Suppress("MemberVisibilityCanBePrivate")

package me.bristermitten.reflector

import me.bristermitten.reflector.factory.property.DefaultPropertyFactory
import me.bristermitten.reflector.factory.property.DefaultPropertySearcher
import me.bristermitten.reflector.factory.property.PropertyFactory
import me.bristermitten.reflector.factory.property.PropertySearcher
import me.bristermitten.reflector.factory.structure.DefaultStructureFactory
import me.bristermitten.reflector.factory.structure.StructureFactory

/**
 * Builder class for [Reflector]
 * By default this uses all default implementations, but is fully customisable for improved functionality.
 */
class ReflectorBuilder {

    var propertyFactory: PropertyFactory by computedByDefault {
        DefaultPropertyFactory()
    }

    var propertySearcher: PropertySearcher by computedByDefault {
        DefaultPropertySearcher(propertyFactory)
    }

    var structureFactory: StructureFactory by computedByDefault {
        DefaultStructureFactory(propertySearcher)
    }

    /**
     * @return a new [Reflector] with the builder's settings
     */
    fun build(): Reflector {
        return Reflector(structureFactory, propertySearcher)
    }
}
