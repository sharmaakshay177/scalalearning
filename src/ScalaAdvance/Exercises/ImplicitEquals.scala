package ScalaAdvance.Exercises

import ScalaAdvance.Lectures.TypeClasses.Equal

object ImplicitEquals extends App{

  object EqualNew{
    def apply[T](a: T, b: T)(implicit equalizer: Equal[T]): Boolean = ???
  }

}
