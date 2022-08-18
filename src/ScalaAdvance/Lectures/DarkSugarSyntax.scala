package ScalaAdvance.Lectures

import ScalaAdvance.Exercises.MyStream

object DarkSugarSyntax extends App{

  def singleArg(arg: Int): String = s"$arg little ducks...."

  val description = singleArg{
    // do some computation
    42
  }

  val list = List(1,2,3)
  val l2 = list.map{ num =>
    num * 10
  }

  println(l2)

  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance = new Action{
    override def act(x: Int): Int = 42 + x
  }

  val aFuncAction: Action = (x : Int) => x + 42

  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello scala")
  })

  val aThreadAgain: Thread = new Thread(() => println("Hello scala again"))

  abstract class AnAbstractClass{
    def implemented: Int = 23
    def f(a: Int): Unit
  }

  val anAbstractInstance: AnAbstractClass = (a: Int) => println(a)

  // syntax sugar #3 :: and #:: methods are specials
  /*
    Scala Spec : last char decides the associativity of the method (either left or right)
    1 :: 2 :: 3 :: List(4,5)
   */
  List(4.5).::(1).::(2).::(3)


  class MyStreamLoc[T]{
    def -->:(value: T): MyStreamLoc[T] = this
  }

  val myStream = 1 -->: 2 -->: 3 -->: new MyStreamLoc[Int]

  // syntax sugar #4 : Multi word naming
  class TeenGirl(name: String){
    def `and then said`(gossip: String = "Always "): Unit = println(s"$name said this $gossip")
  }

  val girl = new TeenGirl("aditi")
  girl `and then said` "Scala is sweet!!!"

  // syntax sugar #5: infix types
  class Composite[A, B]
  val composite: Composite[Int, String] = ???

  val composite2: Int Composite String = ???

  // syntax sugar #6: Update method much like apply
  val array = Array(1,2,3)
  array(2) = 7 // rewritten to array.update(2,7)

  // syntax sugar #7: setter for mutable container
  class Mutable{
    private var internalMember: Int = 0 // private for OO Encapsulation
    def member = internalMember // getter
    def member_=(value: Int): Unit = internalMember = value // setter
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42





















}
