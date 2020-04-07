package caseClassWorking

object weekend{
  def getDayFromNumber(num:Int):String = {
    num match {
      case 1 => "Monday"
      case 2 => "Tuesday"
      case 3 => "wednesday"
      case _ => "only first three defined"
    }
  }
  def instanceMatching(instance:Any):Any = {
    instance match {
      case x:Map[String,String] => instance.isInstanceOf[Map[String,String]]
      case y:List[Int] => instance.isInstanceOf[List[Int]]
      case _ => "some thing else is passed"
    }
  }
}

object patternMatching extends App{
  val mapinstance:Map[String,String] =Map[String,String](
    "firstname"-> "akshay","lastname" -> "sharma")
  val instanceofList:List[Int] = List(1,2,3)

  val isMapinst = weekend.instanceMatching(mapinstance)
  val isListinst = weekend.instanceMatching(instanceofList)
  println(isMapinst)
  println(isListinst)

}
