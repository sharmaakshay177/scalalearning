package ScalaCourseLectures.part1Lectures

object ExpressionsScala extends App{
  val anum:Int = 10
  val bnum:Int = 20

  val aDecision = if(bnum>anum) bnum else anum
  print(aDecision)

  val aNameLengthDecision = {
    val first:String = "akshay"
    val second:String = "sharma"

    if(first.equals(second)) 1 else "no equals"
  }

  print(aNameLengthDecision)
}
