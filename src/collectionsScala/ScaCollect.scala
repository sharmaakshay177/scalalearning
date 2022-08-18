package collectionsScala

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/*
    +   adds an element
    +:  prepend
    :+  append
    ++  concatenates two collections
    -, -- remove element,elements
    ::  makes a new list from a given head and tail
    Vector(1, 2, 3) :+ 5 // Yields Vector(1, 2, 3, 5)
    1 +: Vector(1, 2, 3) // Yields Vector(1, 1, 2, 3)
Mapping,Folding,zipping are useful techniques
An Iterable is any collection that can yield an
Iterator with which you can access all elements in the collection
 */
/*
  flatMap if you want to concatinate all results
 */
object collectionUse extends App {
  def sq(a:Int):Long = a*a

  val arr = ArrayBuffer(1,2,3)
  val permutationArr = arr.permutations
  println()
  val combinationArr = arr.combinations(1)
  combinationArr.foreach(println(_))
  val combinationArr2 = arr.combinations(2)
  combinationArr2.foreach(println(_))
  val combinationArr3 = arr.combinations(3)
  combinationArr3.foreach(println(_))

  val names: List[String] = List("Peter", "Paul", "Mary")
  val namesArr: Array[String] = Array("Peter", "Paul", "Mary")
  namesArr.mapInPlace(_.toUpperCase).foreach(println(_))
  val completeName: ListBuffer[String] = new ListBuffer[String]

}
