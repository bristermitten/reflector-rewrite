package me.bristermitten.reflector.config

interface NamingStrategy {
    fun getterName(fieldName: String, fieldType: Class<*>): String
    fun setterName(fieldName: String, fieldType: Class<*>): String

    companion object {
        var strategy: NamingStrategy = DefaultNamingStrategy
    }
}
