package Leonteq

object LeonteqSolution {
  def oddCelling(p :Int): Int ={
    val result = (p.toFloat / 2.toFloat).ceil.toInt
    result
  }

  def main(args: Array[String])={
    import scala.io.StdIn._
    import scala.collection.mutable._
    val N = 2//readInt()
    val K = 32//readInt()
    val listBuffer = ListBuffer[Int](6,2)
//    for(i <- 1 to N){
//      val ele = readInt()
//      listBuffer.+=(ele)
//    }
    var local = listBuffer.toList.toArray
    //decision Making loop
    for (i <- 1 to K){
      val max = local.max
      val indexMax = local.indexOf(max)
      val min = local.min
      val indexMin = local.indexOf(min)

      //working for Max case
      if(max % 2 != 0){
        val newele = (max + 1 ) / 2
        local.update(indexMax, newele)
      }else{
        val newele = max / 2
        local.update(indexMax, newele)
      }

      //working for min case
        val newmin = min*2
        local.update(indexMin, newmin)
      local = local.sorted
    }
    val sum = local.sum
    println(sum)
  }

}
