package ScalaAdvance.Lectures

object TypeClasses {

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

object AnotherTypeClasses extends App {
  trait HtmlWriteable{
    def toHtml: String
  }

  case class User(name: String, age: Int, email: String) extends HtmlWriteable{
    override def toHtml: String = s"<div>$name $age <a href=$email</div>"
  }

  trait HtmlSerializer[T]{
    def serialize(value: T): String
  }

  implicit object UserSerializer extends HtmlSerializer[User]{
    override def serialize(value: User): String = s"<div>${value.name} ${value.age} <a href=${value.email}</div>"
  }
  // date serializer
  import java.util.Date
  object DateSerializer extends HtmlSerializer[Date]{
    override def serialize(value: Date): String = s"<div>${value.toString}</div>"
  }

  // static type checking
  trait MyTypeClassTemplate[T, B]{
    def action(value: T): String
    def anotherAction(value: T): B
  }

  trait Equal[T]{
    def isEqual(value1: T, value2: T): Boolean
  }

  val user1 = User("abc", 20, "abc@org.com")
  val user2 = User("abc", 20, "abc@org.com")
  val user3 = User("xyz", 21, "xyz@org.com")

  implicit object NameEquality extends Equal[User]{
    override def isEqual(value1: User, value2: User): Boolean = value1.name == value2.name
  }

  object NameEmailEquality extends Equal[User]{
    override def isEqual(value1: User, value2: User): Boolean = (value1.name == value2.name) && (value1.email == value2.email)
  }

  println(NameEquality.isEqual(user1, user2))
  println(NameEquality.isEqual(user1, user3))

  println(NameEmailEquality.isEqual(user1, user2))
  println(NameEmailEquality.isEqual(user1, user3))

  // Part2
  object HtmlSerializer{
    def serializable[T](value: T)(implicit serializer: HtmlSerializer[T]): String = serializer.serialize(value)
  }

  implicit object IntSerializer extends HtmlSerializer [Int]{
    override def serialize(value: Int): String = s"<div style color=blue>$value</div>"
  }

  println(HtmlSerializer.serializable(42))
  println(HtmlSerializer.serializable(user1))

  object Equal{
    def isEqual[T](value1: T, value2: T)(implicit equalityCondition: Equal[T]): Boolean = equalityCondition.isEqual(value1, value2)
  }

  println(Equal.isEqual(user1, user2))
  // if using apply method inside the object can be called directly without any method name


}
