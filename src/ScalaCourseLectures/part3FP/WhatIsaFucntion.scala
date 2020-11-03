package ScalaCourseLectures.part3FP

object WhatIsaFunction extends App{
  val concatString = new Function2[String, String, String] {
    override def apply(s1: String, s2: String): String = s1 + "--" + s2
  }

  val specialFunction: Function1[Int, Function1[Int,Int]]= (v1: Int) => (v2: Int) => v1 * v2

  val specialFunctione: Function1[Int, Function1[Int, Int]] = (a :Int) => (b: Int) => a * b

  val result = specialFunction(10)
  val result2 = result(10)
  println(specialFunctione(10)(10))
  
}
