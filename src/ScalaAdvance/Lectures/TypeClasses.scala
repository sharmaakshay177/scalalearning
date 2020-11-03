package ScalaAdvance.Lectures

object TypeClasses extends App{

  trait Equal[T]{
    //def apply(a: T, b: T): Boolean = ???

    def equalName(val1: T, val2: T): Boolean
    def equalComplete(val1: T, val2: T): Boolean
  }

  case class User(name: String, email: String)

  object UserComparison extends Equal[User]{
    // only name comparison
    def equalName(val1: User, val2: User): Boolean =
      if (val1.name.equals(val2.name)) true
      else false
    // complete object comparison
    def equalComplete(val1: User, val2: User): Boolean =
      if (val1.name.equals(val2.name) & val1.email.equals(val2.email)) true
      else false
  }

  val shubham = User("shubham","abc@xyz.com")
  val anotherShubham = User("shubham","abc@xyz.com")

  val nameComparison = UserComparison.equalName(shubham, anotherShubham)
  println(nameComparison)
  val completeComparison = UserComparison.equalComplete(shubham, anotherShubham)
  println(completeComparison)

}
