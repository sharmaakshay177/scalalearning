package ScalaAdvance.Lectures

object PimpUpImplicits extends App {

  implicit class RichString(val string: String) extends AnyVal {
    def asInt: Int = string.toInt
    def encrypt: String = string.map(ch => (ch.toInt + 2).toChar )
  }

  println("1".asInt)
  println("akshay".encrypt)

  implicit class EnrichInt(val value: Int) extends AnyVal{
    def isEven: Boolean = value % 2 == 0
    def squareRoot: Double =  Math.sqrt(value)
    def square: Double = value * value
  }

  println(42.isEven)
  println(42.squareRoot)
  println(42.square)
}
