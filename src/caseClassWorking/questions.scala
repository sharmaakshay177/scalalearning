package caseClassWorking

case class Article(name:String,amount:Double)

case class Multiple(quantity:Int,article:Article)

object questions extends App{

  val article1 = Article("Blackwell Toaster",29.95)
  val mul = Multiple(10,article1)
  
}
