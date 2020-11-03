package FunctionalProgramming

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

trait implementFunctions{
  // to check if the sub sequence exists in the given sequence
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean
  def unNestList[A](list: List[A]): List[A] // to unNest the provided list with multiple list inside
}

object FunctionalProgrammingUtil {

  def indexAt(array: Array[BigInt], num: BigInt): Int ={
    @tailrec
    def go(i: Int):Int ={
      if (i >= array.length) -1
      else if (array(i) == num) i
      else go(i+1)
    }
    go(0)
  }
  def inOrder[A](a: Int, b: Int): Boolean ={
    if (a < b) true
    else false
  }
  def isSorted[A](array: Array[A], ordered: (A,A) => Boolean): Boolean={
    val length = array.length
    @tailrec
    def go(i : Int): Boolean ={
      if (i == length-1) true
      else if (ordered(array(i), array(i+1))) go(i+1)
      else false
    }
    go(0)
  }
  def map[B,A](list: List[A])(f: A => B): List[B]={
    @tailrec
    def go(head: A, tail: List[A], acc: ListBuffer[B]): ListBuffer[B]={
      if (tail.isEmpty) acc += f(head)
      else go(tail.head, tail.tail, acc += f(head))
    }
    go(list.head, list.tail, new ListBuffer[B]).toList
  }
  def filter[A](list: List[A])(f: A => Boolean): List[A] ={
    @tailrec
    def go(head: A, tail: List[A], acc: ListBuffer[A]): ListBuffer[A]={
      if (tail.isEmpty){
        if(f(head)) acc += head
        else acc
      }
      else{
        if (f(head)) go(tail.head, tail.tail, acc += head)
        else go(tail.head, tail.tail, acc)
      }
    }
    go(list.head, list.tail, new ListBuffer[A]).toList
  }
  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] ={ //similar to map
    //modified the method definition...
    val local =  new ListBuffer[B]
    for (item <- as){
      val result = f(item)
      result.map(item => local.+=(item))
    }
    local.toList
  }
  def zipWith[A,B](listFirst: List[A], listSecond: List[A])(f: (A, A) => B)(implicit ev: ClassTag[A]): List[B] ={
    val result = new ListBuffer[B]
    val listOne = listFirst.toArray
    val listTwo = listSecond.toArray
    if (listFirst.lengthCompare(listSecond.length) == 0){
      // case where length of first is equal to second
      for (i <- 0 until listFirst.length){
        result += f(listOne(i), listTwo(i))
      }
    }
    else if (listFirst.lengthCompare(listSecond.length) < 0){
      // case where listFirst is less than listSecond
      for (i <- 0 until listFirst.length){
        result += f(listOne(i), listTwo(i))
      }
    }
    else if (listFirst.lengthCompare(listSecond.length) > 0){
      // case where listFirst is greater than listSecond
      for (i <- 0 until listSecond.length){
        result += f(listOne(i), listTwo(i))
      }
    }
    result.toList
  }
  def hasSubsequence[A](sup: List[A], sub:List[A]): Boolean={
    // will always check for continued subSequence
    @tailrec
    def matchList(supHead: A, supTail: List[A], subHead: A, subTail: List[A]): Boolean={
      if (supHead == subHead) {
        if (subTail.isEmpty) true
        else matchList(supTail.head, supTail.tail, subTail.head, subTail.tail)
      }
      else false
    }

    @tailrec
    def go(head: A, tail: List[A]): Boolean ={
      if (head == sub.head && tail.nonEmpty) matchList(head, tail, sub.head, sub.tail)
      else if (tail.isEmpty) false
      else go(tail.head, tail.tail)
    }
    go(sup.head, sup.tail)
  }
//  def unNestList[A](list: List[A]): List[A]={
//
//    def go(head: A, tail: List[A], acc: ListBuffer[A]): ListBuffer[A]={
//      if (tail.nonEmpty){
//        head match{
//          case num: A => go(tail.head, tail.tail,acc += num)
//          case innerList: List[A] => go(tail.head, tail.tail,go(innerList.head, innerList.tail, acc))
//        }
//      }
//      else acc
//    }
//
//    go(list.head, list.tail, new ListBuffer[A]).toList
//  }

  def check(any: Any): Any={
    any match {
      case a: Int => a
      case list:List[Any]=> list.head
      case (a: Int,b:Int) => a+b
      case (a: String, b: String) => a+ "--" +b
      case m:Map[String, Int] => m.head
      case _ => "Not from the default list"
    }
  }

}

object DriveCode extends App{
  import FunctionalProgrammingUtil._

  val array = List[Int](20,30,12,4567,548347584).toArray
  val array2: Array[Int] = Array(10,20,41,30,50)
  val nested = List(List(1,2,3),10,List(5,6,7,8))

  def sum(a: Int):Int = a + 10
  println(map(array2.toList)(sum))

  val even = (x: Int) => {
    if (x % 2 == 0) true
    else false
  }
  println(filter(array2.toList)(even))

  val makeDouble = (x: Int) => {
    //make a list with two times element
    val result = x match {
      case num: Int => List(num, num)
    }
    result
  }
  println(flatMap(array2.toList)(makeDouble))

  val tuple = (x: Int, y:Int) => (x, x+y)
  println(zipWith(List(1,2,3,4,5),List(10,10,10,10))(tuple))

  val mainList = List(10,20,30,40,50,60)
  val l1 = List(10,20,30,50)
  val l2 = List(20,30)
  val l3 = List(50)
  val l4 = List(10,20)
  val l5 = List(80)
  val finalList = List(l1,l2,l3,l4,l5)
  for (item <- finalList){
    println(item + " " + hasSubsequence(mainList, item))
  }

}