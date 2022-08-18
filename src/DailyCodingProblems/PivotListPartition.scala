package DailyCodingProblems

object PivotListPartition extends App{
  // https://betterprogramming.pub/the-only-programming-interview-question-you-need-to-prepare-for-e604c4c4d5eb
  val pivot = 10
  val lst = List(9, 12, 3, 5, 14, 11, 11)

  val lessThanPivot = lst.filter(_ < pivot)
  val equalToPivot = lst.filter(_ == pivot)
  val greaterThanPivot = lst.filter(_ > pivot)
  // refactor conditions here a bit messy.....
  var all: List[Int] =_
  if (lessThanPivot.nonEmpty  && equalToPivot.nonEmpty && greaterThanPivot.nonEmpty)
    all = lessThanPivot.appendedAll(equalToPivot.appendedAll(greaterThanPivot))
  else if(lessThanPivot.isEmpty  && equalToPivot.nonEmpty && greaterThanPivot.nonEmpty)
    all = equalToPivot.appendedAll(greaterThanPivot)
  else if(lessThanPivot.nonEmpty  && equalToPivot.isEmpty && greaterThanPivot.nonEmpty)
    all = lessThanPivot.appendedAll(greaterThanPivot)
  else if(lessThanPivot.nonEmpty  && equalToPivot.nonEmpty && greaterThanPivot.isEmpty)
    all = lessThanPivot.appendedAll(equalToPivot)
  else all = List.empty

  println(all)

}
