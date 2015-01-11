package actorthread

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

/**
 * It works like this:
 * 
 * Thrower >>ping>> Forwarder >>pang>> Returner >>pong>> Thrower
 */
object PingPangPongMain extends App {
  
  val quantity = 100
  val delayMillis = 100
  val system: ActorSystem = ActorSystem("PingPangPong")
  val returner: ActorRef = system.actorOf(Props[Returner], "Returner")
  val forwarder: ActorRef = system.actorOf(Props(classOf[Forwarder], returner), "Forwarder")
  system.actorOf(Props(classOf[Thrower], quantity, delayMillis, forwarder), "Thrower")
 
  println("up an running")
}