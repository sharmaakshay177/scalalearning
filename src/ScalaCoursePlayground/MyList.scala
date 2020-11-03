package ScalaCoursePlayground

import scala.annotation.tailrec

/*
  head first element
  last last element
  isEmpty
  Add(int) -> new list with element
  override toString
   */
/*
   New methods to Add
   def foldRight(f: (Int, Int) => Int) : Int
   def reverse: MyList[A] // will reverse the list
   def append(ele: A): MyList[A] // will add the element to the end of the list
   def transform(f: A => B): MyList[B] // will take a function to transform a element
   def exists(f: A => Boolean): Boolean // returns true if any element of this passes the predicate f
   def forall(f: A => Boolean): Boolean —Returns true if and only if all elements of this pass the predicate f

*/

class EmptyListException extends Exception{
  override def toString: String = "List is Empty No Element To Perform Operation"
}

abstract class MyList[A]{
  def head : A
  def last : MyList[A]
  def length: Int
  def isEmpty: Boolean
  def add(ele:A): MyList[A]
  def printElement: String
  def sum: Int
  def product: Double
  def at(i: Int): A // will return the element at the given index
  def indexOf(ele: A): Int // will return the index of the given element
  override def toString: String = "[" + printElement + "]"
  def tail: MyList[A] // to remove the first element from the list
  def drop(n: Int): MyList[A] // to remove the given number from the List
  def dropWhile(f: A => Boolean): MyList[A] // to remove the element based on predicate
}

class Empty[A] extends MyList[A]{
   def head: A = throw new NoSuchElementException
   def last: MyList[A] = throw new NoSuchElementException
   def length: Int = 0
   def isEmpty: Boolean = true
   def add(ele: A): MyList[A] = new Cons(ele, new Empty)
   def sum: Int = throw new EmptyListException
   def product: Double = throw new EmptyListException
   def at(i: Int): A = throw new EmptyListException
   def indexOf(ele: A): Int = throw new EmptyListException
   def printElement: String = ""
   def tail: MyList[A] = throw new EmptyListException
   def drop(n: Int): MyList[A] = throw new EmptyListException
   def dropWhile(f: A => Boolean): MyList[A] = throw new EmptyListException
}

class Cons[A](h: A, t: MyList[A]) extends MyList[A]{
  def head : A = h
  def last : MyList[A] = t
  def isEmpty: Boolean = false
  def add(ele:A): MyList[A] = new Cons(ele, this)
  def tail: MyList[A] = {
    if (t.isEmpty) new Empty
    else t
  }
  def length: Int = {
    @tailrec
    def go(i: Int, t: MyList[A]):Int ={
      if (t.isEmpty) i
      else go(i+1, t.tail)
    }
    go(1, this.t)
  }
  def printElement: String ={
    @tailrec
    def go(h: A, t: MyList[A], acc: String): String ={
      if (t.isEmpty) acc.concat(h.toString)
      else go(t.head, t.last, acc.concat(h.toString + " "))
    }
  go(this.h,this.t, "")
  }

  def drop(n:Int): MyList[A] ={
    @tailrec
    def go(index:Int ,t:MyList[A]): MyList[A] ={
      if (index == n) t
      else go(index + 1, t.tail)
    }
    go(1, this.t)
  }

  def dropWhile(f: A => Boolean): MyList[A] ={
    @tailrec
    def go(h: A, t: MyList[A], acc: MyList[A]): MyList[A] ={
      if (t.isEmpty){
        if (f(h)) acc.add(h)
        else acc
      }
      else if (f(h)) go(t.head, t.tail, acc.add(h))
      else go(t.head, t.tail, acc)
    }
    go(this.h, this.t,new Empty)
  }
//  def sum: Int ={
//    import Numeric.Implicits._
//    implicit def +(x: A)(implicit n: Numeric[A]) = n.toInt(x)
//    @tailrec
//    def go(sum: Int,h: A,t: MyList[A]): Int={
//      if (t.isEmpty) sum + h
//      else go(sum + h, t.head, t.tail)
//    }
//    go(0,this.h, this.t)
//  }
//  def product: Double ={
//    @tailrec
//    def go(acc: Double, h: A, t:MyList[A])(implicit num: Numeric[A]): Double={
//      val result = new java.lang.Double(num.toDouble(h))
//      if (h == 0 || h == 0.0) 0.0
//      else if (t.isEmpty) acc * result
//      else go(acc * result, t.head, t.tail)
//    }
//    go(1.0, this.h, this.t)
//  }

  override def at(index: Int): A ={
    //index will start from 0
    @tailrec
    def go(i: Int, h:A, t: MyList[A]): A ={
      if(t.isEmpty){
        if (i == index) h
        else (-1).asInstanceOf[A]
      }
      else if (i == index) h
      else go(i+1, t.head, t.tail)
    }
    go(0, this.h, this.t)
  }


  override def indexOf(ele: A): Int ={ //index is taken from 0
    @tailrec
    def go(h: A, t: MyList[A], i: Int): Int={
      if(t.isEmpty){
        if(h == ele) i
        else -1
      }
      else if(h == ele) i
      else go(t.head, t.tail, i+1)
    }
    go(this.h, this.t, 0)
  }

  override def sum: Int = ???

  override def product: Double = ???
}

object ListTesting extends App{

  val list = new Cons(2, new Cons(3, new Cons(4, new Cons(5, new Empty))))
  val single = new Cons(50,new Empty)
  val listWithZero = new Cons(10, new Cons(20, new Cons(0, new Cons(30,new Empty))))
  println(list)
  val droppedList = list.drop(2)
  println(droppedList)

  val even = list.dropWhile(x => x % 2 == 0)
  println(even)
  println(list.length)
  println("sum = "+ list.sum)
  println("Non Zero List product = " + list.product)
  println("Non Zero List product = " + listWithZero.product)

  println(list.at(5))
  println(list.indexOf(10))
}


