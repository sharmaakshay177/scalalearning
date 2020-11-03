package ScalaAdvance.Lectures

object AdvanceFunctionalProgramming extends App{

  val aPartialFunction  = new PartialFunction[Int,Int] {
    override def apply(v1: Int): Int = ???
    override def isDefinedAt(x: Int): Boolean = ???
  }

  val aChatBotFunction: PartialFunction[String, String] = {
    case "hi" | "hi there" => "Hi i am your chat bot"
    case "your name" | "what is your name" => "hi my name is Akshi and i am here for your help"
    case "your language" | "language you can speak" => "i can speak english"
    case _ => "please pass a valid string to process"
  }

  scala.io.Source.stdin.getLines().foreach(line => println(aChatBotFunction(line)))

}
