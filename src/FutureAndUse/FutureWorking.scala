package FutureAndUse
/*
 Future and Promise traits
 */
import java.time._

import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureWorking extends App{
  val f1:Future[Int] = Future{
    Thread.sleep(100)
    println(s"this is the future at ${LocalTime.now}")
    10
  }

  val result = Await.result(f1,10.seconds)
  val Some(t) = f1.value
  t match {
    case Success(v) => println(s"the answer is $v")
    case Failure(ex) => println(ex.getMessage)
  }



}
