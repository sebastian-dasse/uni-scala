package futurkombin

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.Promise
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.control.Exception

class FutureCombineImpl extends FutureCombine {
  
  import concurrent.ExecutionContext.Implicits.global
  
  /**Liefert eine Future mit dem Ergebnis der als erste terminierenden Future aus {futures}.
   * Die Ergebnisse aller anderen Futures aus {futures} werden ignoriert.
   * Wenn keine Future innerhalb des Timeout-Zeitraums terminiert, wird das Funktionsergebnis ebenfalls einen Timeout bewirken.*/
  def firstOf[T](futures: Seq[Future[T]]): Future[T] = {
    val promise: Promise[T] = Promise()
    for (future <- futures) {
      future.onComplete {
        case _ => promise.tryCompleteWith(future)
      }
    }
    promise.future
  }
  
  /**Liefert eine Future mit einer Sequenz der Ergebnisse aller Futures aus {futures}.
   * Wenn eine Future aus {futures} versagt oder nicht innerhalb des Timeout-Zeitraums terminiert, 
   * versagt auch die gelieferte Future mit einer Exception. */
  def allOf[T](futures: Seq[Future[T]]): Future[Seq[T]] = {
    val promise: Promise[Seq[T]] = Promise()
    var successCount: Int = 0
    for (future <- futures) {
      future.onComplete{
        case Success(_) =>
          successCount += 1
          if (successCount == futures.length) {
            promise.trySuccess(futures.map(fut => Await.result(fut, 0.seconds)))
          }
    	case Failure(exc) =>
    	  promise.tryFailure( exc )
      }
    }
    promise.future
  }

}