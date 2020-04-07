package HigherOrderFunction

object HOFTestOneDefine{
  def mulBy(factor:Double) = (x : Double) => factor * x
}

object HOFTestOneUse extends App{

  val triple = HOFTestOneDefine.mulBy(3)
  println(triple(10).toInt)

}