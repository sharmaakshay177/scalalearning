package questions.Scala99Problems.Easy

import scala.collection.mutable.{ListBuffer, Stack}
import util.control.Breaks._ // using breakable for continue and break statements
object MiniListUtils{
  implicit def option2Iterable[A](a: A): Iterable[A] = Option.option2Iterable(Some(a))

  //Will take a position and List
  def KthElement(pos: Int, list: List[Int]): Int ={
    val ele = list.apply(pos)
    ele
  }

  // to reverse a list
  def reverseList(list: List[Int]): List[Int] ={
    val revList = list.reverse
    revList
  }

  // to check if a list is palindrome
  // this will work correctly only for odd size of the list
  def isPalindrome(list: List[Int]): Boolean ={
    //TODO Also make this working for all sizes
    val length = list.length
    val mid = length/2
    val stack:Stack[Int] =new Stack[Int]()

    for(i <- 0 to length-1){
      breakable{
        if(i != mid && i < mid){
          stack.push(list.apply(i)) // push if i less than mid
        }else if(i != mid && i > mid){
          val top = stack.top
          if(top == list.apply(i)){ // check if top element and current element are same
            stack.pop() // if same pop the elements
          }else{
            return false
          }
        }else if(i == mid) {
          break // break in case of i == mid value
        }
      }
    }

    if(stack.isEmpty) true
    else false
  }

  //Flatten a nested list
  def doListFlatten(list: List[Any]): List[Any] ={
    val flattenList = list.flatten
    flattenList
  }

  //Eliminate the duplicate elements
  def removeDuplicates[A](list: List[A]): List[A] ={
    //TODO update stack to queue and remove reverse
    val stack  = new Stack[A]()
    for(ch <- list){
      if(stack.isEmpty){
        stack.push(ch)
      }else{
        breakable{
          val top = stack.top
          if(top == ch) break
          else stack.push(ch)
        }
      }
    }//end of for loop
    val endResult = stack.toList.reverse
    endResult
  }

  //To duplicate a element in the List
  def duplicate(list: List[Any]): List[Any] ={
    val newList: ListBuffer[Any] = ListBuffer[Any]()
    list.map(ch => {
      newList +=(ch)
      newList +=(ch)
    })
    newList.toList
  }

  //Remove Kth element from the List
  def removeKthElement(k: Int, list: List[Any]): (List[Any], Any) ={
    val array = list.toArray
    val element = array(k)
    def removeElement(ele: Any, list: List[Any]): List[Any] = list.diff(List(ele))
    val newList = removeElement(element, list)
    Tuple2(newList, element)
  }

  //Get Count of the elements in the sequence
//  def getSequenceElementCount[A,B](list: List[A]): List[B]={
//    val stack = new Stack[Any]()
//    for(ch <- list){
//      if(stack.isEmpty){
//        //val pair = (ch,1)
//        stack.push(Tuple2(ch,1))
//      }else{
//        val top = stack.top
//      }
//    }
//  }
}

object ListProblems extends App{
  import MiniListUtils._

  //Initiating a List
  val list:List[Int] = List[Int](1,1,2,3,5,8)
  val palindromeList: List[Int] = List[Int](1,2,3,2,1)
  val palindromeList2: List[Int] = List[Int](1,2,3,5,3,2,1)
  val listInList: List[Any] = List[Any](List(1,1),2,List(3,List(5,8)))
  val duplicateList: List[Symbol] = List[Symbol]('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
  val listNum = (1 to 10).toList
  val tupleFromFunction = removeKthElement(1, listNum)
  println(tupleFromFunction._1.mkString("--"), tupleFromFunction._2)
}
