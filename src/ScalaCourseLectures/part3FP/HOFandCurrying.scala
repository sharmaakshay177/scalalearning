package ScalaCourseLectures.part3FP

object HOFandCurrying extends App{
  def fun(a: Int, b: Int):Int = a+b
  def toCurry(f: (Int, Int) => Int): Int => Int => Int = a => b => f(a,b)

  def square(num: Int):Int = num*num
  def divideByNum(num:Int):Int = num/10

  def compose(f: Int => Int)(g: Int => Int) = (x: Int)  => f(g(x))
  def antiCompose(f: Int => Int)(g: Int => Int) = (x: Int)  => g(f(x))

  val result = compose(divideByNum)(square)
  val finalResult = result(10)
  println(finalResult)

}
