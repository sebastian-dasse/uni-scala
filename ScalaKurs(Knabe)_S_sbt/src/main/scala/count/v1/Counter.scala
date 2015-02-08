package count.v1

import akka.actor.ActorLogging
import akka.actor.Actor

object Counter {
  case class Number(n: Int)
  case object Finish
  case class Report(evens: Int, odds: Int)
}

class Counter extends Actor with ActorLogging {
  
  import Counter._
  
  var evens = 0
  var odds = 0
  
  def receive(): Receive = {
    case Number(n) =>
      val even = n % 2 == 0
      val clazz = if (even) "even " else "odd"
	  log.info(s"Number $n is $clazz")
      if (even) evens += 1 else odds += 1
    case Finish => sender ! Report(evens, odds)
  }
}