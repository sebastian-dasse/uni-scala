package actorthread.v1

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

/**
 * It works like this:
 * 
 * Thrower >>ping>> Forwarder >>pang>> Returner >>pong>> Thrower
 */
object PingPangPongMain extends App {
  
  private val quantity = 100
  private val delayMillis = 100
  private val system: ActorSystem = ActorSystem("PingPangPong")
  private val returner: ActorRef = system.actorOf(Props[Returner], "Returner")
  private val forwarder: ActorRef = system.actorOf(Props(classOf[Forwarder], returner), "Forwarder")
  private val thrower: ActorRef = system.actorOf(Props(classOf[Thrower], quantity, delayMillis, forwarder), "Thrower")
 
  println("up an running")
}