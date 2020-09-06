package questions

import scala.collection.mutable.Stack

import scala.io.StdIn._

object BalancedBrackets {
  def charCompare(top: Char, current: Char): Boolean ={
    if(top == '{' && current == '}') {return true }
    if(top == '[' && current == ']') {return true}
    if(top == '(' && current == ')') {return true}
    false
  }

  def checkString(string: String): String ={
    val stack: Stack[Char]  = new Stack[Char]()
    var retState : String = null
    for(char <- string){
      if(char == '{' || char == '[' || char == '('){
        stack.push(char)
      }else {
        var top = stack.head
        val decision = charCompare(top, char)
        if(decision){
          stack.pop()
        }else{
          retState = "NO"
        }
      }
    }
    if(stack.isEmpty){retState = "YES" }
    else {retState = "NO"}
    retState
  }

  def main(args: Array[String]): Unit ={
    val size = readInt()
    for(i <- 1 to size){
      val string = readLine()
      val decision = checkString(string)
      println(decision)
    }
  }
}
