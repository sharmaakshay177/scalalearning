package questions

import scala.io.StdIn._
import scala.collection.mutable.ListBuffer
object Question2 {
  def main(args: Array[String]): Unit ={
    val kthList = new ListBuffer[Int]()
    val k = 3//readInt()
    for(i <- 1 to k){
      val num = readInt()
      kthList += (num)//(num)
    }
    val sortedKthList = kthList.sorted
    val lastEle = sortedKthList.last
    //println(lastEle)
    val numberOfDays = lastEle + 2
    print(numberOfDays)
  }
}//89931 //       