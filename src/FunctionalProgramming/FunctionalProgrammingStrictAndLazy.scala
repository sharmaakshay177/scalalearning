package FunctionalProgramming

trait ImplementStrictAndLazyMethods{

}

object ConceptDescription{
  def strictnessDescription: String ={
  """
      | Non-Strictness is a property of a function.
      | It Just means that the function may choose not to evaluate one or
      | more of its arguments.
      |
      | In contrast a strict function always evaluate its signature first.
      |
      | Any Function in scala will be strict.
      |
      | in scala && , || these can also be used as functions that may choose not
      | to Evaluate their arguments
      |""".stripMargin
  }
  def lazyDescription: String ={
    """
      | Adding Lazy keyword to a val declaration will cause scala to delay
      | evaluation of the right-hand side of the lazy val declaration
      | until it's first reference.
      |
      | It will also cache the result so that subsequent references to it
      | don't trigger repeated evaluation.
      |""".stripMargin
  }
}

object FunctionalProgrammingStrictAndLazy {

  def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue else onFalse

}

object DriverCodeStrictAndLazy extends App{
  import FunctionalProgrammingStrictAndLazy._

  if2(10 < 22 , println('a'), println('b'))

}