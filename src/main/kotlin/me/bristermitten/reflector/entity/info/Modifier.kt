package me.bristermitten.reflector.entity.info

/**
 * All modifiers that Reflector supports.
 * This is an amalgamation of Java and Kotlin modifiers that uses a bitmask for optimal performance
 * Note that if a modifier is applicable across language, it will be applied. For example,
 * a Java class that is not `final` will have the Kotlin [OPEN] modifier
 */
enum class Modifier(val mask: Int) {
    //Java compatibility layer
    PUBLIC(0x00000001),
    PRIVATE(0x00000002),
    PROTECTED(0x00000004),
    STATIC(0x00000008),
    FINAL(0x00000010),
    SYNCHRONIZED(0x00000020),
    VOLATILE(0x00000040),
    TRANSIENT(0x00000080),
    INTERFACE(0x00000200),
    ABSTRACT(0x00000400),
    STRICTFP(0x00000800),
    //end Java compatibility layer
    //intermediate (Kotlin & Java)
    //Note that while some of these Modifiers are not present in the Java Modifier spec, they are included for compatibility and resolved manually.
    ENUM(0x00001000),
    ANNOTATION(0x00002000),
    //end intermediate
    //Kotlin compatibility layer
    INTERNAL(0x00004000),
    OPEN(0x00008000)
}
