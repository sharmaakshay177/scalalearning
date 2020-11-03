package ScalaAdvance.Exercises

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean){
  def apply(elem : A): Boolean = contains(elem)
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(anotherSet: MySet[A]): MySet[A]
  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit

  //part2
  def -(elem: A): MySet[A]
  def --(anotherSet: MySet[A]): MySet[A] // difference
  def &(anotherSet: MySet[A]): MySet[A] // intersection

  //part3
  def unary_! : MySet[A]
}

class EmptySet[A] extends MySet[A]{
  def contains(elem: A): Boolean = false
  def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)
  def ++(anotherSet: MySet[A]): MySet[A] = anotherSet
  def map[B](f: A => B): MySet[B] = new EmptySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]
  def filter(predicate: A => Boolean): MySet[A] = this
  def foreach(f: A => Unit): Unit = ()
  //part2
  def -(elem: A): MySet[A] = this
  def --(anotherSet: MySet[A]): MySet[A] = this
  def &(anotherSet: MySet[A]): MySet[A] = this
  //part3
  def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)
}

class PropertyBasedSet[A](property: A => Boolean) extends MySet[A]{
  override def contains(elem: A): Boolean = property(elem)
  override def +(elem: A): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || x == elem)
  override def ++(anotherSet: MySet[A]): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  override def map[B](f: A => B): MySet[B] = politelyFail
  override def flatMap[B](f: A => MySet[B]): MySet[B] = politelyFail
  override def foreach(f: A => Unit): Unit = politelyFail

  override def filter(predicate: A => Boolean): MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))

  override def -(elem: A): MySet[A] = filter(x => x !=  elem)
  override def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)
  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  def politelyFail = throw new IllegalArgumentException
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A]{

  def contains(elem: A): Boolean = head == elem || tail.contains(elem)

  def +(elem: A): MySet[A] =
    if (this contains elem) this
    else new NonEmptySet[A](elem, this)

  def ++(anotherSet: MySet[A]): MySet[A] =
    tail ++ anotherSet + head

  def map[B](f: A => B): MySet[B] = tail.map(f) + f(head)

  def flatMap[B](f: A => MySet[B]): MySet[B] = tail.flatMap(f) ++ f(head)

  def filter(predicate: A => Boolean): MySet[A] ={
    val filteredTail = tail.filter(predicate)
    if (predicate(head)) filteredTail + head
    else filteredTail
  }

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  def -(elem: A): MySet[A] =
    if (head == elem) tail
    else tail.-(elem) + head

  def --(anotherSet: MySet[A]): MySet[A] =
    if (! anotherSet.contains(head)) tail.--(anotherSet) + head
    else tail.--(anotherSet)
  /*
    another definition for & = filter(x => anotherSet.contains(x))  can be reduced to filter(anotherSet)
   */
  def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  def unary_! : MySet[A] = new PropertyBasedSet[A](x => !this.contains(x))
}

object MySet{
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(valSeq: Seq[A], accumulator: MySet[A]): MySet[A] ={
      if (valSeq.isEmpty) accumulator
      else buildSet(valSeq.tail, accumulator + valSeq.head)
    }
    buildSet(values.toSeq, new EmptySet[A])
  }
}

object MySetTesting extends App{
  val mySetList = MySet.apply(1,2,3)
  val mySetList2 = MySet(4,3,5)
  println(mySetList.contains(1))
  mySetList.foreach(elem => print(elem + " "))
  println()
  val removedElemSet = mySetList.-(3)
  removedElemSet.foreach(elem => print(elem + " "))
  println()
  val intersectionSet = mySetList.&(mySetList2)
  intersectionSet.foreach(elem => print(elem + " "))
  println()
  val differenceSet = mySetList.--(mySetList2)
  differenceSet.foreach(elem => print(elem + " "))

}