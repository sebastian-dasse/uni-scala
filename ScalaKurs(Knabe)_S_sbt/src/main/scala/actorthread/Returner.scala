package actorthread

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef

object Returner {
  case class Pang(id: Int, origin: ActorRef)
}

class Returner extends Actor with ActorLogging {
  
  import Returner._
  
  def receive: Receive = {
    case p: Pang =>
      log.info(s"Received $p")
      p.origin ! Thrower.Pong(p.id)
  }
}