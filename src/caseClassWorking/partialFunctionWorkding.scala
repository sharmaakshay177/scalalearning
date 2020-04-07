package caseClassWorking
/*
partial function a function which may not be defined for all
inputs
PartailFunction[A,B](A is the argument and b is the return type
 */


object partialFunctionWorkding extends App{

  val fun: PartialFunction[Char,Int] = {
    case '+' => 1
    case '-' => -1
    case _ => 0
  }

  val ifpassed = fun('*')
  println(ifpassed)

}