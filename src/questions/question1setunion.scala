package questions
import scala.io.StdIn._
object question1setunion extends App{
  val n:Int = 25//readInt()
  val a:BigInt = 3//readLong()
  val b:BigInt = 5//readLong()

  val numList:List[Int] = (1 to n).toList
  println(numList)
  val divBya:Set[Int] = {for(item<-numList if(item %a==0) )yield item}.toSet
  println(divBya)
  val divByb:Set[Int] = {for(item<-numList if(item %b==0) )yield item}.toSet
  println(divByb)
  val unionSet:Set[Int] = divBya.union(divByb)
  println(unionSet)
  println(unionSet.size)
}
