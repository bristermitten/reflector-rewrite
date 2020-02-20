package me.bristermitten.reflector.factory

import java.util.concurrent.ConcurrentHashMap

/**
 * A cache that is guaranteed to return the same value if the same input is given
 * It incorporates caching to reduce the amount of reflection used.
 *
 * TODO could have some lookup optimizations since we know the types we're getting
 */
class Cache<K, V>(
    private val createFunction: (K) -> V
) {

    private val map: MutableMap<K, V> = ConcurrentHashMap(2)

    operator fun get(key: K): V = map.computeIfAbsent(key, createFunction)


}
