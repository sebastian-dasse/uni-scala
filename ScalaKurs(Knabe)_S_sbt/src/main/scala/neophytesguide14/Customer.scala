package neophytesguide14

import akka.actor.Actor
import akka.actor.ActorRef

case object CaffeineWithdrawalWarning

class Customer(caffeineSource: ActorRef) extends Actor {
  def receive = {
    case CaffeineWithdrawalWarning => caffeineSource ! EspressoRequest
    case Bill(cents) => println(s"Cus: I have to pay $cents, or else!")
  }
}