package ScalaCourseLectures.part3FP

object MapFlatMapWorking extends App{

  def checkCharInList(char: Char): Boolean ={
    if (chars.contains(char)) true
    else false
  }

  val numbers = List[Int](1,2,3,4)
  val chars = List[Char]('a','b','c','d')
  val colors = List[String]("black","white")

  val combinations = numbers.flatMap(num => chars.flatMap(char => colors.map(col => ""+num+char+"-"+col )))
  val newCombinations = numbers.filter(_ % 2 ==0 ).flatMap(num => chars.flatMap(char => colors.map(col => ""+num+char+"-"+col )))
  val colorCombinations = numbers.filter(_ % 2 == 0).flatMap(num => chars.flatMap(char => colors.filter(_ == "black").map(col => ""+num+char+"-"+col)))
  println(colorCombinations)


  val forCombinations = for{
    num <- numbers if num %2 == 0
    char <- chars
    color <- colors if color == "black"
  } yield ""+num+char+"-"+color

  println(forCombinations)

  val numberMulTen = numbers.map{
    number => number*10
  }
  println(numberMulTen)
}
