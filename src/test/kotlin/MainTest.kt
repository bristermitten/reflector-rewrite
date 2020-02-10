import me.bristermitten.reflector.Reflector
import me.bristermitten.reflector.entity.Structure
import org.junit.Test
import kotlin.system.measureNanoTime

class MainTest {
    @Test
    fun test() {
        var structure: Structure<Employee>

        val reflector = Reflector.create()

        val time = measureNanoTime {
            structure = reflector.structureOf()
        }

        println("Created Structure in ${time}ns")

        val time2 = measureNanoTime {
            structure = reflector.structureOf()
        }

        println("Fetched Structure in ${time2}ns")


        structure = reflector.structureOf()

        val employee = Employee("Daniel Perkins", "dp12", 300000.00)

        val propertyAccessTime = measureNanoTime {
            structure.properties.forEach {
                it.get(employee)
            }
        }
        println("Fetched all properties in ${propertyAccessTime}ns")

        val kMutableProperty1 = Employee::name

    }
}


data class Employee(
    var name: String,
    var id: String,
    val salary: Double
)


