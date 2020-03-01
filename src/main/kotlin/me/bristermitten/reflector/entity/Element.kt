package me.bristermitten.reflector.entity

import me.bristermitten.reflector.entity.info.Info

interface Element<T> {
    val name: String
    val type: Class<T>
    val info: Info
}
