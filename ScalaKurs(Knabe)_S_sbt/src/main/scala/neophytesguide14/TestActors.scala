package neophytesguide14

import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.Future

object TestActors {
  def main(args: Array[String]) {
    val system: ActorSystem = ActorSystem("Barista")
    val barista: ActorRef = system.actorOf(Props[Barista], "Barista")
    val customer: ActorRef = system.actorOf(Props(classOf[Customer], barista), "Customer")
    customer ! CaffeineWithdrawalWarning
    barista ! ClosingTime
    
    println("initial messages were sent...")
    
    implicit val timeout = Timeout(2.seconds)
    implicit val ec = system.dispatcher
    val f: Future[Any] = barista ? CappuccinoRequest
    f.onSuccess {
      case Bill(cents) => println(s"App: Will pay $cents cents for a cappuccino")
    }
    
    barista ? CappuccinoRequest
  }
}