package ScalaAdvance.Lectures

object Currying extends App{

  val simpleAddFunction  = (x: Int, y: Int) => x + y
  def simpleAddMethod(x: Int, y: Int): Int = x + y
  def curriedAddMethod(x: Int)(y: Int): Int = x + y

  val add7 = (y: Int) => 7 + y
  println(add7(10))

  val add7_1 = simpleAddFunction(7,_)
  println(add7_1(1))

  val add7_2 = simpleAddMethod(7,_)
  println(add7_2(2))

  val add7_3 = curriedAddMethod(7)(_)
  print(add7_3(3))

  val formats = List("%4.2f","%8.6f","%14.12f")
  def add(x: Double, y: Double) = x + y
  val numlist = List(10002.35288,10.258,102.558,102.658)
  val sum = numlist.reduceLeft(add)
  println(sum)
  def representationWithDiffFormats(x: Double)(formatRep: String): String =  formatRep.format(x)
  val passFormatTo = representationWithDiffFormats(sum)(_: String)
  formats.map(format => println(passFormatTo(format)))

}
