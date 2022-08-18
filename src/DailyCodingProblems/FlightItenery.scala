package DailyCodingProblems

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object FlightItenery extends App{

  val map1WithPath = Map("SFO" -> "HKO", "YYZ" -> "SFO", "YUL" -> "YYZ", "HKO" -> "ORD")
  val map1WithNonPath = Map("SFO" -> "COM", "COM" -> "YYZ", "HKO" -> "ORD")
  val map2withPath = Map(("A", "B"), ("A", "C"), ("B", "C"), ("C", "A"))

  def createVisitedMap(sourceDestinationMap: Map[String, String]): mutable.HashMap[String, Boolean] = {
    val visitedMap = mutable.HashMap.empty[String, Boolean]
    val allNodes = ListBuffer.empty[String]
    for (item <- sourceDestinationMap){
      if(allNodes.isEmpty && item._1 != item._2) {
        allNodes.addOne(item._1)
        allNodes.addOne(item._2)
      }
      else{
        if (!allNodes.contains(item._1)) allNodes.addOne(item._1)
        if (!allNodes.contains(item._2)) allNodes.addOne(item._2)
      }
    }
    allNodes.foreach(place => visitedMap.addOne(place, false))
    visitedMap
  }

  def checkIfItinerariesExist(source: String, sourceDestinationMap: Map[String, String]): Option[List[String]] = {
    val pathList = ListBuffer.empty[String]
    val visited = createVisitedMap(sourceDestinationMap)

    pathList.addOne(source)
    visited.update(source, true)
    var nextDestination = sourceDestinationMap.getOrElse(source, null)
      while (nextDestination != null && !visited.getOrElse(nextDestination, true)){
        pathList.addOne(nextDestination)
        visited.update(nextDestination, true)
        nextDestination = sourceDestinationMap.getOrElse(nextDestination, null)
      }
    if(pathList.length == 2 || pathList.length == 1) None
    else Some(pathList.toList)
  }


  val path1 = checkIfItinerariesExist("YUL", map1WithPath)
  println(path1)
  val path2 = checkIfItinerariesExist("COM", map1WithNonPath)
  println(path2)
  val path3 = checkIfItinerariesExist("A", map2withPath)
  println(path3)

}
