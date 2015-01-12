package actorthread.v2

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef

object Returner {
  case class Pang(id: Int, origin: ActorRef)
  case class Start(origin: ActorRef)
}

class Returner extends Actor with ActorLogging {
  
  import Returner._
  
  var thrower: ActorRef = ActorRef.noSender
  
  def receive: Receive = {
    case s: Start =>
      log.info(s"Received $s")
      thrower = s.origin
      thrower ! Thrower.Start()
    case p: Pang =>
      log.info(s"Received $p")
      thrower ! Thrower.Pong(p.id)
  }
}