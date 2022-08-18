package caseClassWorking

case class Article(name: String, amount: Double)

case class Multiple(quantity: Int, article: Article){
  def TotalAmount: Double = quantity * article.amount
}

object questions extends App{

  val article1 = Article("Blackwell Toaster",29.95)
  val mul = Multiple(10,article1)

  println(mul.TotalAmount)
  
}
