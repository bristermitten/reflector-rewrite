# Reflector
Reflector is a type safe wrapper for Java Reflection.

It provides fast access to all of the "properties" of a class along with type-safe getting and setting

For example:
```java

Reflector reflector = Reflector.create();
Structure<Employee> structure = reflector.structureOf(Employee.class);
Property<Employee, String> nameProperty = structure.getProperties().getByName("name");
Employee employee = new Employee("John Smith", 30, 30000);

nameProperty.get(employee); //John Smith

class Employee {
    private String name;
    private int age;
    private int salary;
    //Getters, setters, constructor, etc
}
```


Reflector also has first class support for Kotlin

```kotlin
val property: Property<Employee, String> = reflector.getProperty(Employee::name)
```


Currently, Reflector only supports property access. 
A property is defined as a field, with an optional getter and setter.

That is, normal methods won't be wrapped by Reflector at all.

So where is Reflector useful?

- Serialization - Reflector is able to easily iterate over all fields in a class, and get and set their values appropriately
- Bean Copying - Properties can be easily converted between owner classes, making bean copying 
