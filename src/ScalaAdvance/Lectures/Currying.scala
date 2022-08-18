package ScalaAdvance.Lectures

object Currying{

  val simpleAddFunction  = (x: Int, y: Int) => x + y
  def simpleAddMethod(x: Int, y: Int): Int = x + y
  def curriedAddMethod(x: Int)(y: Int): Int = x + y

  val add7 = (y: Int) => 7 + y
  println(add7(10))

  val add7_1 = simpleAddFunction(7,_)
  println(add7_1(1))

  val add7_2 = simpleAddMethod(7,_)
  println(add7_2(2))

  val add7_3 = curriedAddMethod(7)(_)
  print(add7_3(3))

  val formats = List("%4.2f","%8.6f","%14.12f")
  def add(x: Double, y: Double) = x + y
  val numlist = List(10002.35288,10.258,102.558,102.658)
  val sum = numlist.reduceLeft(add)
  println(sum)
  def representationWithDiffFormats(x: Double)(formatRep: String): String =  formatRep.format(x)
  val passFormatTo = representationWithDiffFormats(sum)(_: String)
  formats.map(format => println(passFormatTo(format)))

}


class CurriedClass(a: Int, b:Int, c:Int, funcPassed: () => String)(d: String, e: String){
  def printFirstArgs: Unit = println(s"$a - $b - $c")
  def printSecondArgs: Unit = println(s"$d - $e")
  def resultOfFunctionPassed: Unit = println(funcPassed())
}

object AnotherCurrying extends App{
  // curried functions
  val superAdder: Int => Int => Int = x => y => x + y

  // add 7 exercise
  val add7One: Int => Int = (x : Int) => x + 7
  def add7Two(x:Int): Int = x + 7
  def add7Three(x: Int = 7)(y: Int): Int = x + y

  val add7_1 = add7One(7)
  val add7_2 = add7Two(7)
  val add7_3 = add7Three(7) _

  // underscores are powerful
  def concatinate(a: String, b: String, c: String): String = a + b + c
  val inserName = concatinate("Hi my name is ", _: String, " how are you?")
  println(inserName("Akshay"))

  // functions vs methods
  // params by-name vs 0-lambda

  def byName1(n: Int): Int = n + 1
  def byName(n: => Int): Int = n + 1
  def byFunction(f: () => Int) = f() + 1

  def method: Int = 42
  def paranMethod(): Int = 42

  /*
    calling byName and ByFunction
    -  int
    - method
    - paranmethod
    - lambda
    - PAF
   */
  val someLambda = (x: Int) => x

  val callingByIntWithByName = byName(10)
  //val callingByIntWithByFunction = byFunction(10)

  val callingByMethodWithByName = byName(method)
  //val callingByMethodWithByFunction = byFunction(method)

  val callingByParamMethodWithByName = byName(paranMethod())
  val callingByParamMethodWithByFunction = byFunction(paranMethod)

  val callingByLambdaWithByName = byName((() => 42)())
  val callingByLambdaWithByFunction = byFunction(() => 42)

  //val callingByPAFWithByName = byName(paranMethod _)
  val callingByPAFWithByFunction = byFunction(paranMethod _)


  def returnName(): String = "Akshay"

  // working with curryied class
  val obj = new CurriedClass(10,20,30, () => "AkshaySharma")("String1", "String2")
  obj.printFirstArgs
  obj.printSecondArgs
  obj.resultOfFunctionPassed















}
