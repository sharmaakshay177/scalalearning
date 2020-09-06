package questions.Scala99Problems.Medium
import scala.collection.mutable.ListBuffer
import util.control.Breaks.{breakable}
import scala.math._


object UtilityPackage{
  def duplicateGivenTimes(num: Int, list: List[Any]): List[Any] ={
    val newList = ListBuffer[Any]()
    list.map(sym => {
      for(i <- 1 to 3){
        newList.+=(sym)
      }
    })
    newList.toList
  }
  def dropEveryNthElement(num: Int, list: List[Any]): List[Any] ={
    val newList = ListBuffer[Any]()
    var counter = 1
    for (item <- list){
      breakable{
        if (counter % num == 0){
          counter = counter + 1
        }else{
          newList +=(item)
          counter = counter + 1
        }
      }
    }
    newList.toList
  }
  def sliceFromList(l: Int, k:Int ,list: List[Any]): List[Any] = list.slice(l,k)
  def rotate(num: Int, list: List[Any]): List[Any] ={
    /*
    Have Not handled the case if given number is greater than the
    Given Length of the list.
     */
    val listNew = ListBuffer[Any]()
    if(num < 0){
      val reverseList = list.reverse
      val take = reverseList.take(abs(num))
      val slice = reverseList.slice(abs(num), reverseList.length)
      listNew.++=(slice).++=(take).reverse.toList
    }else{
      val take = list.take(num)
      val slice = list.slice(num, list.length)
      listNew.++=(slice).++=(take)
      listNew.toList
    }

  }

}


object DuplicateCharByCount extends App{
  import UtilityPackage._
  val list: List[Char] = List[Char]('a','b','c')
  val finalList = duplicateGivenTimes(3, list)
  val numList = (1 to 20).toList
  val dropNthTime = dropEveryNthElement(4, numList).toString()
  val slicedList = sliceFromList(4,10,numList).toString
  val rotated = rotate(-3,numList).mkString("--")
  println(rotated)



}
