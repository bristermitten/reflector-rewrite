package me.bristermitten.reflector.config

object DefaultNamingStrategy : NamingStrategy {

    override fun getterName(fieldName: String, fieldType: Class<*>): String {
        val name = fieldName.capitalize()
        if (fieldType == Boolean::class.java) {
            return "is$name"
        }
        return "get$name"
    }

    override fun setterName(fieldName: String, fieldType: Class<*>): String {
        val name = fieldName.capitalize()
        return "set$name"
    }
}
