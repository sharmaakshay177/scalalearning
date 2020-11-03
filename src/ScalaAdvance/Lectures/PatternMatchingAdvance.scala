package ScalaAdvance.Lectures
/*
 for custom pattern matching we can use our own companion objects
 and define a unapply method in there
 */

object PatternMatchingAdvance extends App{

  class Person(val name: String, val age: Int)

  object Person{
    def apply(name: String, age: Int): Person = new Person(name, age)
    def unapply(person: Person): Option[(String, Int)] = Some((person.name, person.age))
  }

  object IsEven{
    def unapply(arg: Int): Boolean = arg % 2 ==0
  }
  val bob = Person("bob", 25)
  val x:Int = 10
  val comparison = x match {
    //case Person(name, age) => s"given name is ${name} and age is ${age}"
    case IsEven() => "given number is even."
    case _ => "Odd number if not even"
  }

  println(comparison)
}
