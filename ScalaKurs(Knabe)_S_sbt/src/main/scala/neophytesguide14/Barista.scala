package neophytesguide14

import akka.actor.Actor
import akka.actor.ActorRef

sealed trait CoffeeRequest
case object CappuccinoRequest extends CoffeeRequest
case object EspressoRequest extends CoffeeRequest

case class Bill(cents: Int)
case object ClosingTime

class Barista extends Actor {
  var cappuccinoCount = 0
  var espressoCount = 0
  def receive = {
    case CappuccinoRequest => 
      sender ! Bill(250)
      cappuccinoCount += 1
      println(s"Bar: I have to prepare cappuccino #$cappuccinoCount!")
    case EspressoRequest =>
      sender ! Bill(200)
      espressoCount += 1
      println(s"Bar: Let's prepare espresso #$espressoCount.")
    case ClosingTime => 
      println("Bar: I will close now.")
      context.system.shutdown()
  }
}