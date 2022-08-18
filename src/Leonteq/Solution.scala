package Leonteq

import scala.collection.mutable.ListBuffer

object Solution extends App{

  val strList = "203 204 205 206 207 208 203 204 205 206"
  val listBuffer = strList.split(" ").map(item => item.toInt).toBuffer
  val la = listBuffer.length

  val strList2 = "203 204 204 205 206 207 205 208 203 206 205 206 204"
  val listBuffer2 = strList2.split(" ").map(item => item.toInt).toBuffer
  val lb = listBuffer2.length

  if (lb > la) {
    listBuffer2.map{
      item =>
        if (listBuffer.contains(item)) {
          listBuffer2.-=(item)
          listBuffer -= item
        }
    }
  } else {
    listBuffer.map{
      item =>
        if (listBuffer2.contains(item)) {
          listBuffer -= item
          listBuffer2 -= item
        }
    }
  }

  listBuffer.foreach(println)
  println("end of smaller ")
  listBuffer2.foreach(println)
}
