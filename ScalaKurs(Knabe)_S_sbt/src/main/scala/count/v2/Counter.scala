package count.v2

import akka.actor.Actor
import akka.actor.ActorLogging

object Counter {
  case class Number(n: Int)
  case class Finish()
  case class Report(evens: Int, odds: Int)
}

class Counter extends Actor with ActorLogging {
  
  import Counter._
  
  private var evens = 0
  private var odds = 0
  
  def receive(): Receive = {
    case Number(n) =>
      val even = n % 2 == 0
      if (even) evens += 1 else odds += 1
      val clazz = if (even) "even" else "odd"
      log.info(s"Number was $clazz")
    case Finish => sender ! Report(evens, odds)
  }
}