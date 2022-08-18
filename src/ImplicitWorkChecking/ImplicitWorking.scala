package ImplicitWorkChecking

class percentageFactor(num:Int){
  def getPercentFactor:Double = num*0.7
}

class SomeUtility{
  def giveList(start:Int, end:Int): List[Int] = (start to end).toList
  def sendAnyNumber: Int = 248
  def anyOtherOneDigitNumber: Int = 4
}


object ImplicitWorking {

  def sum(num:Int)(implicit num2:percentageFactor):Unit = {
    println("the sum of given and implicit is ")
    print(s"${num+num2.getPercentFactor}")
  }

  def fullName(firstName: String)(implicit lastname: String): String ={
    s"$firstName $lastname"
  }
}

object DoSomeFunction{
  def getSomeNumbers(any: String)(implicit another: SomeUtility): String ={
    s"$any - ${another.giveList(1,10).mkString("-")}"
  }
  def getSomeNumber(any: String)(implicit another: SomeUtility): String ={
    s"$any - ${another.sendAnyNumber}"
  }
}

object ImplicitWorkingUse extends App {
  implicit val numberCentFactor:percentageFactor = new percentageFactor(10)
  ImplicitWorking.sum(10)

  implicit val lastname: String = "sharma"
  val name = ImplicitWorking.fullName("akshay")
  println(name)

  implicit val utility: SomeUtility = new SomeUtility()
  val getNumList = DoSomeFunction.getSomeNumbers("Numbers")
  println(getNumList)
}
