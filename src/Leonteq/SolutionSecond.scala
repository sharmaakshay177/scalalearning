package Leonteq

import scala.collection.mutable._

object SolutionSecond {
  def makeCountListOfList(list: List[Int]): List[List[Any]]={
    val sum = list.sum
    var local = (1 to sum).toArray
    val holdLocal = ListBuffer[List[Any]]()
    for (item <- list){
      val temp = local.take(item).toList
      holdLocal.+=(temp)
      local = local.drop(item)
    }
    holdLocal.toList
  }

  def getBoxForMarbel(list: List[List[Any]], marbel: Long):Int ={
    var local = 0
    var result = -1
    for(item <- list){
      local = local +1
      if(item.contains(marbel)) {result = local}
    }
    result
  }

  def main(args: Array[String]):Unit ={
    import scala.io.StdIn._
    import scala.collection.mutable._

    val N = readLong()// number of boxes
    val marbleCountList = ListBuffer[Int](2,5,3)
    //list of count of marbles
//    for (i <- 1 to N){
//      val marbleCount = readLong()
//      marbleCountList.+=(marbleCount)
//    }
    val pair = makeCountListOfList(marbleCountList.toList)
      for(item <- pair){
        println(item)
      }
    //queries
    val Q = 3//readLong() //number of queries
    for (i <- 1 to Q){
      val marbleIndex = readLong()
      val result = getBoxForMarbel(pair, marbleIndex)
      println(result)
    }
  }

}
