package ScalaAdvance.Lectures

object PimpUpImplicits extends App {

  implicit class RichString(val string: String) extends AnyVal {
    def asInt: Int = string.toInt
    def encrypt: String = string.map(ch => (ch.toInt + 2).toChar )
  }

  println("1".asInt)
  println("akshay".encrypt)

  implicit class EnrichInt(val value: Int) extends AnyVal{

  }
}
