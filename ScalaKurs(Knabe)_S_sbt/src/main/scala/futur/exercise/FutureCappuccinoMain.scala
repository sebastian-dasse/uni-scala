package futur.exercise
  
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Random
import scala.concurrent.Await

import futur.exercise.prepared._ // QUESTION Why is this necessary ???

//scala.concurrent.Future[T] kapselt eine Berechnung, die eventuell in der Zukunft fertig wird.

/**Introduction of Futures from 
 * http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html*/

object FutureCappuccinoMain extends App {
  
    /** Man benötigt einen ExecutionContext implizit verfügbar, 
     *  in dem die Future-Aktionen ausgeführt werden.
     *  Der implizite ExecutionContext global basiert auf einem Thread-sparenden ForkJoinPool.
     * http://blog.jessitron.com/2014/02/scala-global-executioncontext-makes.html
    */
    import scala.concurrent.ExecutionContext.Implicits.global
	
	def grind(beans: CoffeeBeans) = Future[GroundCoffee]{
	  slowly("grinding"){s"ground coffee of $beans"}
	}
	
	def heatWater(water: Water) = Future[Water]{
	  slowly("heating"){water.copy(temperature = 85)}
	}
	
	def frothMilk(milk: Milk) = Future[FrothedMilk]{
	  slowly("frothing"){s"frothed $milk"}
	}
	
	def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
	  slowly("brewing"){"espresso"}
	}
	
	def combine(espresso: Espresso, frothedMilk: FrothedMilk) = Future[String] {
	  slowly("combining"){"cappuccino"}
	}
	
	def prepareCappuccino(): Future[Cappuccino] = {
	  //Starte 3 asynchrone Berechnungen, die sofort eine Future liefern:
	  val groundCoffeeFuture: Future[GroundCoffee] = grind("arabica")
	  val heatedWaterFuture: Future[Water] = heatWater(Water(20))
	  val frothedMilkFuture: Future[FrothedMilk] = frothMilk("milk")
	  val result = for {
	    ground <- groundCoffeeFuture
	    water <- heatedWaterFuture
	    espressoFuture = brew(ground, water)
	    foam <- frothedMilkFuture
	    espresso <- espressoFuture
	    cappucino <- combine(espresso, foam)
	  } yield cappucino
	  result
	}
  
    val result =  Await.result(prepareCappuccino(), 120.seconds)
	println(s"Whole process produced $result")

}