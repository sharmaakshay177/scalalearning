package caseClassWorking
/*
partial function a function which may not be defined for all
inputs
PartailFunction[A,B](A is the argument and b is the return type
 */


object partialFunctionWorking extends App{

  val sum = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1

    override def isDefinedAt(x: Int): Boolean = ???
  }


  val fun: PartialFunction[Char,Int] = {
    case '+' => 1
    case '-' => -1
    case _ => 0
  }

  val ifPassed = fun('*')
  println(ifPassed)

}