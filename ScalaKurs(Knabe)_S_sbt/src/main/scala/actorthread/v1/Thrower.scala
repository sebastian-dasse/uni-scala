package actorthread.v1

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef

object Thrower {
  case class Pong(id: Int)
}

class Thrower(quantity: Int, delayMillis: Int, forwarder: ActorRef) extends Actor with ActorLogging {
  
  import Thrower._
  
  for (id <- 1 to quantity) {
    forwarder ! Forwarder.Ping(id, self)
  }
  var received = 0
  
  def receive: Receive = {
    case p: Pong =>
      received += 1
      log.info(s"Received $p")
      if (received >= quantity) {
        // delayed shutdown, because async logger might have to finish his work before shutdown
        {
          import scala.concurrent.duration._
          context.system.scheduler.scheduleOnce(
            delayMillis.millis, self, "Shutdown"
		  )(context.dispatcher, self)
        }
      }
    case "Shutdown" =>
      log.info(s"Shutdown after receiving $received Pongs")
      context.system.shutdown()
  }
}