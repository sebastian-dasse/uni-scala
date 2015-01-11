package actorthread

import org.junit.Test

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorSystem
import akka.actor.Props

/**
 * Can create up to 1.627.900 Actors
 * (on an Intel i7-3517U CPU @ 1.9h GHz with 4.00 GB RAM under Windows 8.1 Pro).
 */
class ActorCreationTest {
  import ActorCreationTest._
    
  @Test def howManyActors(){
    val system = ActorSystem("UselessActors")
    for(i <- 1 to 10000000) {
      system.actorOf(Props[UselessActor], s"UselessActor_$i")
      if (i % 100 == 0) println(s"UselessActor $i started.")
    }
  }    
}

object ActorCreationTest {
  class UselessActor extends Actor with ActorLogging {
    def receive: Receive = {
      case _ => ()
    }  
  } 
}