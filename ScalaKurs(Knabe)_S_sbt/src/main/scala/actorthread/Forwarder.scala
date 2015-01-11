package actorthread

import akka.actor.ActorLogging
import akka.actor.Actor
import akka.actor.ActorRef

object Forwarder {
  case class Ping(id: Int, origin: ActorRef)
} 

class Forwarder(returner: ActorRef) extends Actor with ActorLogging {
  
  import Forwarder._
  
  def receive: Receive = {
    case p: Ping => 
      log.info(s"Received $p")
      returner ! Returner.Pang(p.id, p.origin)
  }
}