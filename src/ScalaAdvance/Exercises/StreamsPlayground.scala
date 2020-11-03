package ScalaAdvance.Exercises

import scala.annotation.tailrec

abstract class MyStream[+A]{
  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]

  def #::[B >: A](element: B): MyStream[B] //prepend operator
  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] //concatenate two streams

  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(predicate: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A]
  def takeAsList(n: Int): List[A] = take(n).toList()

  @tailrec
  final def toList[B >: A](acc: List[B] = Nil): List[B] = {
    if (isEmpty) acc.reverse
    else tail.toList(head :: acc)
  }
}

object EmptyStream extends MyStream[Nothing]{
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException
  def tail: MyStream[Nothing] = throw new NoSuchElementException

  def #::[B >: Nothing](element: B): MyStream[B] = new Cons(element, this) //prepend operator
  def ++[B >: Nothing](anotherStream: => MyStream[B]): MyStream[B] = anotherStream //concatenate two streams

  def foreach(f: Nothing => Unit): Unit = ()
  def map[B](f: Nothing => B): MyStream[B] = this
  def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = this
  def filter(predicate: Nothing => Boolean): MyStream[Nothing] = this

  def take(n: Int): MyStream[Nothing] = this
}

class Cons[+A](h: A, tl: => MyStream[A]) extends MyStream[A] {
  def isEmpty: Boolean = false

  override val head: A = h
  override lazy val tail: MyStream[A] = tl

  def #::[B >: A](element: B): MyStream[B] = new Cons(element, this) //prepend operator
  def ++[B >: A](anotherStream: => MyStream[B]): MyStream[B] = new Cons(h, tail ++ anotherStream) //concatenate two streams

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  def map[B](f: A => B): MyStream[B] = new Cons(f(head), tail.map(f))

  def flatMap[B](f: A => MyStream[B]): MyStream[B] = f(head) ++ tail.flatMap(f)

  def filter(predicate: A => Boolean): MyStream[A] = {
    if (predicate(head)) new Cons(head, tail.filter(predicate))
    else tail.filter(predicate)
  }

  def take(n: Int): MyStream[A] =
    if (n <= 0) EmptyStream
    else if (n ==1 ) new Cons(head, EmptyStream)
    else new Cons(head, tail.take(n-1))

}

object MyStream{
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new Cons(start, MyStream.from(generator(start))(generator))
}

object StreamsPlayground extends App{
  def odd(x: Int):Int = if(x % 2 !=0) x else -1
  def dropCondition(x: Int):Boolean = if (x == -1) false else true

  val naturals = MyStream.from(2)(_ + 1)
  //val even = naturals.map(_ * 2).take(50).foreach(println)
  val odds = naturals.map(odd).take(50).toList()
  //println(naturals.flatMap(x => new Cons(x + 1, EmptyStream)).take(10).toList())
  //println(odds.filter(dropCondition(_)).take(20))

  /* part 2 Exercise
    1.  apply fibonacci seq
    2. get all prime numbers by using Eratosthenes' sieve
     filter all divisible by 2, then by 3, then by 5
   */
  val naturalAll = MyStream.from(2)(_ + 1)//it will give all natural numbers
  val filterDivisibleBy2 = naturalAll.filter(_ % 2 != 0 ).#::(2)
  val filterDivisibleBy3 = filterDivisibleBy2.filter(_ % 3 != 0).#::(3)
  val filterDivisibleBy5 = filterDivisibleBy3.filter(_ % 5 != 0).#::(5)

  filterDivisibleBy5.take(20).foreach(x => print(x + " "))
  println
  def newPrimeMethod(numbers: MyStream[Int]): MyStream[Int] =
    if (numbers.isEmpty) numbers
    else new Cons[Int](numbers.head, newPrimeMethod(numbers.tail.filter(_ % numbers.head != 0 )))

  println(newPrimeMethod(naturalAll).take(1000).toList())
  println
  /*
  0,1,1,2,3,5,8,13,21,34....
   */
  def fibonacci(first: Int, second: Int): MyStream[Int] =
    new Cons[Int](first, fibonacci(second, second + first))

  println(fibonacci(1,1).take(20).toList())

}
