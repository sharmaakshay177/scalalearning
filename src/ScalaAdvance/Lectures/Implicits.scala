package ScalaAdvance.Lectures

object Implicits extends App{

  case class Person(name: String, age: Int)

  implicit def personsOrderingByName: Ordering[Person] = Ordering.by(person => person.name )
  implicit def personsOrderingByAge: Ordering[Person] = Ordering.by(person => person.age)
  implicit def personOrderingLexographically: Ordering[Person] = Ordering.fromLessThan(
    (person1,person2) => person1.name.compareTo(person2.name) < 0)

  val persons = List(
    Person("Akshay",25),
    Person("Zakir",26),
    Person("Bob",24),
    Person("Joan",35)
  )

  /*println(persons.sorted)
  need any one type of implicit ordering at a given time compiler will decide based on Ordering[Person]
  Ordering Type the we have made and called upon.
   */

  /*
  Exercise
  Ordering by total price
  ordering by unit count
  ordering by unit price
   */
  case class Purchase(nUnits: Int, UnitPrice: Int)
  object Purchase{
    implicit def orderingByTotalPrice: Ordering[Purchase] = Ordering.by(purchase => (purchase.nUnits * purchase.UnitPrice))
  }
  object OrderingByUnits{
    implicit def orderingByUnits: Ordering[Purchase] = Ordering.by(purchase => purchase.nUnits)
  }
  object OrderingByUnitPrice{
    implicit def orderingByUnitPrice: Ordering[Purchase] = Ordering.by(purchase => purchase.UnitPrice)
  }

  //importing ordering according to need

  val itemPurchased = List(
    Purchase(5,100),
    Purchase(10,10),
    Purchase(3,300),
    Purchase(4,40)
  )
  println(itemPurchased.sorted)
}
