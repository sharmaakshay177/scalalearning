package ScalaCourseLectures.part4

object PatternMatching extends App{

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(expr: Expr): String = expr match {
    case Number(x) => s"${x}"
    case Sum(x,y) => show(x) +" + "+ show(y)
    case Sum(Sum(x, y), z) => show(x) + " + " + show(y) +" + "+ show(z)
    case Prod(Sum(x,y ), z) => s"(${show(x)} + ${show(y)}) * ${show(z)}"//(x + y) * z 
    case Sum(Prod(x, y), z) => s"${show(x)} * ${show(y)} + ${show(z)}"//x * y + z
    case _ => "not a good expression"
  }

  val expression = Sum(Number(10), Number(12))
  val output = show(expression)
  println(output)
}
