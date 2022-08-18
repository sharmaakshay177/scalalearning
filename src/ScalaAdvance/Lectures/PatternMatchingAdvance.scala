package ScalaAdvance.Lectures
/*
 for custom pattern matching we can use our own companion objects
 and define a unapply method in there
 */

object PatternMatchingAdvance{

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

object AdvancePatternMatching extends App{
  val numbers = List(1)

  val description = numbers match{
    case head :: Nil => println(s"The only element is $head")
    case head :: tail => println(s"The list contain more than one element where $head and $tail")
    case _ => println(s"Doing Nothing !!!")
  }

  /*
   - constants
   - wildcards
   - case classes
   - tuples
   - some special magic like above
   */

  // Making class compatible with pattern matching
  class PersonAny(val name: String, val age: Int)

  object PersonAny{
    def apply(name: String, age: Int): PersonAny = new PersonAny(name, age)
    def unapply(arg: PersonAny): Option[(String, Int)] =
      if (arg.age < 21) None
      else Some(arg.name, arg.age)

    def unapply(age: Int): Option[String] =
      Some(if (age < 21) "Minor" else "Major")
  }

  val akshay = PersonAny("Akshay", 25)
  val greeting  = akshay match {
    case PersonAny(n, a) => s"Hi My name is $n and I am $a years old."
    case _ => s"Sorry the person is below the age of 21"
  }

  println(greeting)

  val legalStatus = akshay.age match {
    case PersonAny(a) => s"Legal Status : $a"
  }

  println(legalStatus)

  case class Or[A,B](a: A, b: B)
  val either: Or[Int, String] = Or(2, "two")
  val humanDescription = either match {
    case number Or string => println(s"$number is written as $string")
  }














}