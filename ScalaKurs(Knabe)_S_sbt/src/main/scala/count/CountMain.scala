package count

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.Props
import akka.actor.ActorRef

/**
 * Eine Probeklausuraufgabe.
 *
 * @author Christoph Knabe
 * @since 2015-01-23
 */
object CountMain extends App {
  
  /* Gegeben sei dieses Hauptprogramm, welches einen Actor Counter und einen Actor Manager erzeugt.*/
  
  val system = ActorSystem("Counting")  
  val counter: ActorRef = system.actorOf(Props[Counter], "Counter")
  system.actorOf(Props(classOf[Manager], counter), "Manager")

}