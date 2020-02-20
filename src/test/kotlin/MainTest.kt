import me.bristermitten.reflector.GeneratedTest
import me.bristermitten.reflector.Reflector
import me.bristermitten.reflector.entity.Structure
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

class MainTest {
    @Test
    fun test() {
        var structure: Structure<Employee>
        val reflector = Reflector.create()


        val time = measureTimeMillis {
            structure = reflector.structureOf()
        }

        println("Created Structure in ${time}ms")

        val time2 = measureTimeMillis {
            structure = reflector.structureOf()
        }

        println("Fetched Structure in ${time2}ms")


        structure = reflector.structureOf()

        val employee = Employee("Daniel Perkins", "dp12", 300000.00)

        val propertyAccessTime = measureTimeMillis {
            structure.properties.forEach {
                it[employee]
            }
        }

        println("Fetched all properties in ${propertyAccessTime}ms")

        val property = structure.properties[Employee::name]


        println(reflector.getProperty(Employee::name)[employee])
        println(property[employee])


    }

    @Test
    fun testWithGeneratedClass() {
        var size = 1.0
        val employee2 = Employee2()
        val test = GeneratedTest()
        val reflector = Reflector.create()

        val time = measureTimeMillis {
            val structure = reflector.structureOf<GeneratedTest>()
            val structure2 = reflector.structureOf<Employee2>()

            val properties = structure.properties
            size = properties.size.toDouble()

            properties.forEach { property ->
                val value = property[test]
                property[test] = value as Int + 1

                val p = structure2.properties[property]
                if (p != null) p[employee2] = 1
            }
        }

        println("$time ms")
        println("That's ${time / size}ms per property!")

        assertEquals(11, test.a10)
        assertEquals(1, employee2.a2)
    }
}

fun main() {
    MainTest().testWithGeneratedClass()
}

data class Employee2(
    var a2: Int = 3
)

data class Employee(
    var name: String,
    var id: String,
    val salary: Double
)


