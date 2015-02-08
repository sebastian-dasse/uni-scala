package count.v1

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef

/**Actor zur Beauftragung eines Counter-Actors und zur Ausgabe des Ergebnisses.
 *
 * @author Christoph Knabe
 * @since 2014-01-23
 * */
class Manager(counter: ActorRef) extends Actor with ActorLogging {
  /*Gegeben sei außerdem dieser Actor Manager. 
   * Er sendet an den Counter eine Reihe von Number-Nachrichten und abschließend eine Finish-Nachricht.
   * Daraufhin soll er eine Report-Nachricht mit Angabe der Anzahlen von geraden bzw. ungeraden Zahlen
   * in den Number-Nachrichten erhalten.
   * Schreiben Sie den Code zur Deklaration der 3 Nachrichtentypen im Begleitobjekt zu Counter
   * und die Actor-Klasse Counter, die diese Zählung vornimmt.*/
    
  val numbers = List(7, 5, 3, 9, 11, 2, 6)
  numbers foreach (counter ! Counter.Number(_))
  counter ! Counter.Finish 
  
  def receive: Receive = {
    case Counter.Report(evens: Int, odds: Int) => 
      println(s"Received report: evens=$evens, odds=$odds")
      assert(evens==2, s"evens must be 2, but is $evens")
      assert(odds==5, s"odds must be 5, but is $odds")
      context.system.shutdown()
  }

}