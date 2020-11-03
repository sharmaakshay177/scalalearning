package AlarmClock

import scala.concurrent.duration._
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import scala.language.postfixOps

import scala.concurrent.Await

object Description {
  def askDescription: String =
    """ Ask is used to sends a message asynchronously and it returns a Future which represents a possible reply.
      | If the actor does not reply and complete the future, it will expire after the timeout period.""".stripMargin
}

class TakeInput extends Runnable{
   def run(): Unit = {

     def playSound(duration: Int, thread: Thread): Unit={
       val deadline = duration.minutes.fromNow
       thread.start()
       while(deadline.hasTimeLeft()){
         //println("playing sound")
       }
       thread.stop()
       println("stoping after 1 min as not stop found")
     }

     val thread = new Thread()
     playSound(1, thread)

    println(" press 1 or Y to stop alarm")
    val input = scala.io.StdIn.readChar() match {
      case '1' | 'Y' | 'y' => {
        println("stopping alarm")
        thread.stop()
        true
      }
      case _ => {
        println("other input")
        false
      }
    }

  }

}

class ActorSecond extends Actor{
  override def receive: Receive ={
    case message: String => {
      println("hi iam second actor and message received is ")
      println(s"message : $message")
    }
    case "hello" => println("Hi message received by child actor")
  }
}

class MyActor extends Actor {
  val log = Logging(context.system, this)

  override def preStart(): Unit = println("preStart method is called")

  override def postStop(): Unit = println("post stop method is called")

  override def receive: Receive ={
    case '1' | 'Y' | 'y' => {
      println("stopping the song player")
      sender ! "signal send to stop alarm"
    }
    case "hello" => {
      println("Hi there I am root actor and hello is received and sending it to child actor")
      val child = context.actorOf(Props[ActorSecond],"childActor")
      sender ! "hello" //use to send values
    }
    case _ => println("Song player will stop after 1 min default playing")
  }

}

object ThreadTesting  extends App{

  val input = new TakeInput()
  //input.run()

  val actorSystem = ActorSystem("MySystem")
  // actorSystem.terminated() - to terminate the system which hold actors
  // val isTerminated = actorSystem.terminate()
  val myActor = actorSystem.actorOf(Props[MyActor], name = "myActor") // to create the actor instance

  // passing message to the actor and it will send the message to receive method
//  val interrupt = scala.io.StdIn.readChar()
//  myActor ! interrupt
//  val interrupt2 = scala.io.StdIn.readChar()
//  myActor ! interrupt2
  myActor ! "hello"

  implicit val timeout = Timeout(5 second)

//   val toStopPlayer = Await.result(myActor ? "1", 20 second) //myActor ? "1"
//   println(toStopPlayer)


  //myActor.tell("hello", null) // tell or ! is use to send message it works on a fire and forget mechanism

  // stopping the thread
  //actorSystem.stop(myActor)


}
