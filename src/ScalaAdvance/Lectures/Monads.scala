package ScalaAdvance.Lectures
/* Exercise implement a Lazy[T] monad  =  computation which will only be executed when needed
      implement unit/ apply
                flatMap
 */
object Monads extends App{
  class Lazy[+A](value: => A){
    def use: A = value
    def flatMap[B](f: ( => A) => Lazy[B]): Lazy[B] = f(value)
  }
  object Lazy{
    def apply[A](value: => A): Lazy[A] = new Lazy(value)
  }

  val lazyInstance = Lazy{
    println("hi i am checking lazy monads")
    42
  }

  val lazyFlatMapChecking = lazyInstance.flatMap(x => Lazy{
    x * 10
  })






}

