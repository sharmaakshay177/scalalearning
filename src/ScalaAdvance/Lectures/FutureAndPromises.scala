package ScalaAdvance.Lectures

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success, Try}

object FutureAndPromises extends App{

  def immediateFuture[T](value: T): Future[T] = Future(value)

  val random = new Random()
  val futureA = Future{
    Thread.sleep(500)
    14
  }
  val futureB = Future{
    Thread.sleep(100)
    24
  }

  def inSequence[A, B](fa: Future[A], fb: Future[B]): Future[B] = fa.flatMap(_ => fb)
  def first[T](fa: Future[T], fb: Future[T]): Future[T] = {
    val promise = Promise[T]

    def tryComplete(promise: Promise[T], result: Try[T]) = result match {
      case Success(value) => try{
        promise.success(value)
      }catch {
        case _ =>
      }
      case Failure(exception) => try{
        promise.failure(exception)
      }catch {
        case _ =>
      }
    }

    fa.onComplete(promise.tryComplete)
    fb.onComplete(promise.tryComplete)
    promise.future
  }
  def last[T](fa: Future[T], fb: Future[T]): Future[T] = ???

}
