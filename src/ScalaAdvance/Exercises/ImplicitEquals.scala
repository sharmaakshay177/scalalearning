package ScalaAdvance.Exercises

import ScalaAdvance.Lectures.TypeClasses.Equal

import scala.collection.mutable.ListBuffer

object ImplicitEquals{

  object EqualNew{
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean = ???
  }

}

object Question {
  def findGcd(a: Long, b: Long): Long ={
    if (b == 0)
      return a
    findGcd(b, a%b)
  }
  def meetingTimeOfAll(list: List[Long]): Long = {
    var lcm = list.head
    var gcd = list.head

    for (i <- 1 until list.length){
      gcd = findGcd(list(i), lcm)
      lcm = lcm * list(i) / gcd
    }
    lcm
  }

  def main(args: Array[String]) {

  }
}