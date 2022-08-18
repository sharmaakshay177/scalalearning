package caseClassWorking
/*
Case classes are a special kind
of classes that are optimized for use in pattern matching.
 */
/*
 can also have a case objects for singletons
 case object Nothing extends Amount
 */
abstract class Amount

case class Doller(value:Double) extends Amount{
  override def toString: String = {"hi this is Doller value to string"}
}

case class Currency(value:Double,unit:Option[String]) extends Amount

final case class Person(firsName: String, lastName: String){
  def fullName: String = f"$firsName$lastName"
}

object caseClassesUse extends App{
  val d1:Doller = Doller(65.21)
  println(d1.value)
  println(d1.toString)
  val c1:Currency = Currency(100.20,Some("INR"))
  val c2 = Currency(65.25,None)


}