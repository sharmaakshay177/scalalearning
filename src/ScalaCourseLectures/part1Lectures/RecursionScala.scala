package ScalaCourseLectures.part1Lectures

import ScalaCourseLectures.part1Lectures.RecursionScala._
/*
   1. concatinate a string n times
   2. isPrime function using tail recursion
   3. Fibbonachi series using tail recursion
*/

object RecursionScala {
  def concatinateString(time:Int,name:String):String ={
    def concatinationHelper(time:Int,accumulator:String):String={
      if (time <=0 ) accumulator
      else concatinationHelper(time-1, accumulator+name)
    }
   concatinationHelper(time,"")
  }
  val num = 10
  for(u<- 2 to num){
    if (num%u==0){

    }
  }
  def isPrimeChecker(num:Int):Boolean ={

    def isPrimeHelper(num:Int,numlocal:Int,accumulator:Int):Boolean ={
      var count:Int = 0

      if(num == 1 || num== 2) {return true}
      count = if(num%numlocal ==0) accumulator+1 else accumulator

      if(numlocal == num){
        if(count>2){false}
        else {true}
      }
      else isPrimeHelper(num,numlocal+1,count)
    }

    isPrimeHelper(num,1,0)
  }
}

object RecursionScalaMainCode extends App{
  val decision = isPrimeChecker(17)

  if(decision) println("number is prime")
  else println("not a prime number")
}
