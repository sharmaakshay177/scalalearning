package ImplicitWorkChecking

class percentageFactor(num:Int){
  def getPercentFactor:Double = num*0.7
}

object ImplicitWorking {

  def sum(num:Int)(implicit num2:percentageFactor):Unit = {
    println("the sum of given and implicit is ")
    print(s"${num+num2.getPercentFactor}")
  }
}

object ImplicitWorkingUse extends App {
  implicit val numberCentFactor:percentageFactor = new percentageFactor(10)
  ImplicitWorking.sum(10)
}
