package ScalaCourseLectures.part2Lectures

class OverFlowException(message: String) extends Exception{
  override def toString: String = message
}
class UnderFlowException(message: String) extends Exception{
  override def toString: String = message
}
class MathCalculationException(message: String) extends Exception{
  override def toString: String = message
}

object GenericsUse extends App {
  class PocketCalculator{
    def add(numa: Int, numb: Int):Int = {
      val sum = numa + numb
      if (sum > Int.MaxValue) throw new OverFlowException("Greater than Max Value")
      else if (sum < Int.MinValue) throw new UnderFlowException("Lesser than Min Value")
      else sum
    }
    def subtract(numa: Int, numb: Int):Int = numa - numb
    def multiply(numa: Int, numb: Int):Int = numa * numb
    def divide(numa: Int, numb: Int):Int ={
      if(numb == 0) throw new MathCalculationException("Given Denominator is 0")
      else numa / numb
    }
  }

  val pocketCalc = new PocketCalculator
  val operationReturn = try{
    pocketCalc.divide(10,0)
  }catch {
    case _: OverFlowException => "Greater than Max Value"
    case _: UnderFlowException => "Lesser than Min Value"
    case _: MathCalculationException => "Given Denominator is 0"
  }
  println(operationReturn)
}
