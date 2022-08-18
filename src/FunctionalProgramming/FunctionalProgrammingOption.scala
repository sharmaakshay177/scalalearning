package FunctionalProgramming
/*
  The idea here is to use look for algebraic data type Option and Either
  so that we can represent Exceptions as ordinary values and use HOF
  to encapsulate common patters of handling and propagating Errors.
 */

import scala.collection.mutable.ListBuffer


sealed trait Option[+A]
case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

sealed trait Either[+E, +A]
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

trait ImplementMethodOption {
  def lift[A,B](f: A => B): Option[A] => Option[B]
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C]
  def sequence[A](a: List[Option[A]]): Option[List[A]]
  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]]

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]]
  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]]
}


object FunctionalProgrammingOption {

  def description: String ={
    """
      |Option has two cases - It can be defined, in which it will be Some
      |                       or it can be undefined in that case it will be
      |                       None.
      |
      |Using this we can say that return type now reflect the possibility
      |that the result may not always be defined.
      |
      |Can be used in return type to tell something we are returning and or not
      |in the same way we can use it in argument that there is possibility that
      |argument can be there(Passed) or not Passed.
      |""".stripMargin

  }
  def eitherVsOptionDescription: String ={
    """
      | To Handle Failures and Exceptions with ordinary values,
      | and write functions that abstract out common Patterns of error Handling.
      |
      | Now here One Thing to Notice is Option will return us the value or None
      | It will not give us what went wrong in the case of an exceptional condition.
      |
      | All it can do is give us None, indicating that there’s no value to be had.
      | But sometimes we want to know more. For example,
      | we might want a String that gives more information,
      | or if an exception was raised,
      | we might want to know what that error actually was
      |
      |""".stripMargin
  }
  def typeNotation: String ={
    """Syntax:
      |class One[+A]
      |class Two[+A] extends One[A]
      |means that two will always be the subtype of given One.
      |
      |[B :> A] - which means B will always be greater than or equal to
      |the base Subtype A.
      |
      |""".stripMargin
  }
  def mean(xs: Seq[Double]): Option[Double] ={
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)
  }
  def variance(xs: Seq[Double]): Option[Double] ={
    val mean = xs.sum / xs.length
    var sum = 0.0
    xs.foreach(item => sum = sum + math.pow(item -mean, 2))
    Some(sum)
  }
  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A,B) => C): Option[C] ={

    val aLocal = a match {
      case Some(x) => x
      //case None => None[A]
    }
    val bLocal = b match {
      case Some(y) => y
      //case None => None[B]
    }
    if (aLocal == None || bLocal == None ) None
    else Some(f(aLocal, bLocal))
  }
  def sequence[A](a: List[Option[A]]): Option[List[A]] ={
    val local = new ListBuffer[A]
    a.map(item => {
      val res = item match {
        case Some(x) => x
        //case None => None[A]
      }
      local += res
    })
    Some(local.toList)
  }
  def meanWithEither(xs: IndexedSeq[Double]): Either[String, Double] ={
    if (xs.isEmpty) Left("mean of empty List")
    else Right(xs.sum / xs.length)
  }
  def safeDiv(a: Int, b: Int): Either[Exception, Int] ={
    try Right(a / b)
    catch { case e: Exception => Left(e)}
  }


}

object DriveCodeOption extends App{
  import FunctionalProgrammingOption._
  // Description of the case matching

  // checking working of Either
  val result = safeDiv(10, 5)
  // need extraction from Either case
  result match {
    case Right(num) => println(num)
    case Left(exception) => println(exception)
  }

}