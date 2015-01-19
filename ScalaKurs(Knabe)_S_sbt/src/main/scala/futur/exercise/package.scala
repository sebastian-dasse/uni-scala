package futur.exercise

/**Common definitions for both main objects.
 * Follows Tutorial of Daniel Westheide "The Neophyte's Guide to Scala Part 8: Welcome to the Future"
 * http://danielwestheide.com/blog/2013/01/09/the-neophytes-guide-to-scala-part-8-welcome-to-the-future.html
 * Modifications: Without Exceptions.
 * @author Christoph Knabe
 * @since 2014-12-18*/
package object prepared {
  
  // Some type aliases, just for getting more meaningful method signatures:
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  /**Sleeps some time below 5 seconds and then returns the result of the computation of {expression}. */
  def slowly[T](actionName: String)(expression: =>T): T = {
    import scala.util.Random
    println(s"Start $actionName...")
    import scala.concurrent.blocking
    blocking{ //Markiert diesen Codeteil als potentiell zeitverbrauchend. Erlaubt Thread-Allokation bei Bedarf.
//      Thread sleep Random.nextInt(10000)
      readLine(s"$actionName done?\n")
    }
    val result = expression
    println(s"Finished $actionName gave $result")
    result
  }
  
}