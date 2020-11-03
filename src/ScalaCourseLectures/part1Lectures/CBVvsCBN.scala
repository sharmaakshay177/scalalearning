package ScalaCourseLectures.part1Lectures

object CBVvsCBN extends App{

  def callByValue(x: Long):Unit ={
    println(x)
    println(x)
  }

  //: => defines the passing entire things
  def callByName(x : => Long):Unit ={
    println(x)
    println(x)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())
}
