package HigherOrderFunction

object definition {

  def square(num:Int):Int = num*num
  def greater(a: Int, b: Int): Int = if (a > b) a else b
  def mul(a: Int, b: Int): Int = a * b
  def values(fun:(Int)=> Int,low:Int,high:Int) = {
    //TODO add a list for returing a list pair from low till high
     for(i <- low to high ){
       val value = fun(i)
       val squarepair = Tuple2(i,value)
       println(squarepair)
     }
  }
  def factorial(num: Int): Long = {
    val r = num to 1 by -1
    val fact = r.reduceLeft(mul(_,_))
    fact
  }

}

object definitionUse extends App {
  def sum(a:Int,b:Int):Int = a+b
  def sub(a:Int,b:Int):Int = a-b
  def mul(a:Int,b:Int):Int = a*b
  //TODO Fix ajustTpPair after collections
  //def adjustTpPair(fun:(Int,Int) => Int)((a:Int,b:Int))  =
  val pairs = (1 to 10).zip(11 to 20)

}