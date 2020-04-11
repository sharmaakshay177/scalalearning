package TypeParameters

case class Pair[T,S](first:T, second:S)

object GenericMethods {
  def getMiddle[T](arr : Array[T]):T = arr(arr.length / 2)
}

object GenericTypePair extends App{
  val p1 : Pair[String,String] = new Pair[String,String]("Akshay","Sharma")
  println(s"First Name ${p1.first} ans Last Name ${p1.second}")

}
