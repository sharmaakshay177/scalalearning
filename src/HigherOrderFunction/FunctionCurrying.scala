package HigherOrderFunction
/*
currying is the process of turning a function that take two
arguments into a function that takes one argument.
 */

object FunctionCurrying {
  def mulbytwo(a:Int)(b:Int) = a*b
}

object FunctionCurryUse extends App{
  def until(condition: => Boolean) (block: => Unit): Unit ={
    if (!condition){
      block
      until(condition)(block)
    }
  }
  var x:Int = 10
  until(x==2){
    x -=1
    println(x)
  }


}
