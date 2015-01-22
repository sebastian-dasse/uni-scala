package futurkombin

import org.scalatest.junit.AssertionsForJUnit
import scala.collection.mutable.ListBuffer
import collection.mutable.Stack
import org.junit.Assert._
import org.junit.{Test, Before, BeforeClass}
import org.scalatest.Matchers
import java.lang.reflect.ParameterizedType
import scala.concurrent.Future
import scala.concurrent.duration._
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import scala.util.Success
import scala.util.Failure
import scala.concurrent.Await
import scala.util.Try

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class FutureCombineTest extends AssertionsForJUnit with Matchers {
  
  import concurrent.Future
  import concurrent.ExecutionContext.Implicits.global
  import concurrent.Promise
    
  val testee: FutureCombine = new FutureCombineImpl
  
  @Test def t01firstOfSuccesses() {
    val act1 = actionFuture("Saying HELLO", 0){"Hello World,"}
    val act2 = actionFuture("What about", 1000)("Berlin")
    val act3 = actionFuture("How is", 2000)("is great!")
    val result1 = testee.firstOf(Seq(act1, act2, act3))
    Await.result(result1, 3.seconds) shouldBe "Hello World,"
    val result2 = testee.firstOf(Seq(act2, act3, act1))
    Await.result(result2, 3.seconds) shouldBe "Hello World,"
    val result3 = testee.firstOf(Seq(act3, act1, act2))
    Await.result(result3, 3.seconds) shouldBe "Hello World,"
  }
  
  private class AddresseeUnknown extends Exception
  
  /**Waits up to 3 seconds for the completion of {future} and asserts that it has failed with an AddresseeUnknown exception.*/
  private def assertAddresseeUnknown0(future: Future[String]){
    Await.ready(future, 3.seconds)
    future.value match {
      case Some(Failure(x)) if x.isInstanceOf[AddresseeUnknown]=> "as expected"
      case x => fail(x.toString)
    }
  }
  
  /**Waits up to 3 seconds for the completion of {future} and asserts that it has failed with an AddresseeUnknown exception.*/
  private def assertAddresseeUnknown(future: Future[String]){
    assertException(classOf[AddresseeUnknown]){future}
  }
  
  /**Waits up to 3 seconds for the completion of {future} and asserts that it has failed with an exception of the given {exceptionClass}.*/
  private def assertException(exceptionClass: Class[_])(future: Future[_]){
    Await.ready(future, 3.seconds)
    future.value match {
      case Some(Failure(x)) if exceptionClass.isAssignableFrom(x.getClass)=> "as expected"
      case Some(Failure(x)) => fail("Unexpected exception", x)
      case x => fail(x.toString)
    }
  }
  
  @Test def t02firstOfWithFailure() {
    val act1 = actionFuture("Saying HELLO", 555){throw new AddresseeUnknown}
    val act2 = actionFuture("What about", 1000)("Berlin")
    val act3 = actionFuture("How is", 2000)("is great!")
    assertAddresseeUnknown{
      testee.firstOf(Seq(act1, act2, act3))
    }
    assertAddresseeUnknown{
       testee.firstOf(Seq(act2, act3, act1))
    }
    assertAddresseeUnknown{
       testee.firstOf(Seq(act3, act1, act2))
    }
  }
  
  @Test def t03AllOfSuccesses() {
    val act1 = actionFuture("Saying HELLO", 500){"Hello World,"}
    val act2 = actionFuture("What about", 1000)("Berlin")
    val act3 = actionFuture("How is", 2000)("is great!")
    val result1 = testee.allOf(Seq(act1, act2, act3))
    Await.result(result1, 10.seconds) shouldBe Seq("Hello World,", "Berlin", "is great!")
    val result2 = testee.allOf(Seq(act2, act3, act1))
    Await.result(result2, 3.seconds) shouldBe Seq("Berlin", "is great!", "Hello World,")
    val result3 = testee.allOf(Seq(act3, act1, act2))
    Await.result(result3, 3.seconds) shouldBe Seq("is great!", "Hello World,", "Berlin")
  }
  
  @Test def t04allOfWithFailure() {
    val act1 = actionFuture("Saying HELLO", 555){throw new AddresseeUnknown}
    val act2 = actionFuture("What about", 1000)("Berlin")
    val act3 = actionFuture("How is", 2000)("is great!")
    assertException(classOf[Exception]){
      testee.allOf(Seq(act1, act2, act3))
    }
    assertException(classOf[Exception]){
       testee.allOf(Seq(act2, act3, act1))
    }
    assertException(classOf[Exception]){
       testee.allOf(Seq(act3, act1, act2))
    }
  }
  
  @Test def t99GiveAllActionsTimeToComplete() {
    Thread.sleep(3*1000)
  }  
  
  /**This method can be used as a placeholder for code which should return something
   * which should be matched.
   * For example you could write ' XXX shouldBe "Nirvana" ' if you want to compile the test 
   * and replace XXX later by an expression, which returns "Nirvana".
   *  @throws  A `NotImplementedError` always */
  private def XXX = convertToAnyShouldWrapper{
    throw new NotImplementedError("Code-to-test still missing. See 2nd line of stack trace.")
  }   

  /**Sleeps {millis} milliseconds and then returns the result of the computation of {expression}. */
  private def actionFuture[T](actionName: String, millis: Long)(expression: =>T): Future[T] = Future{
    println(s"Start $actionName ...")
    import scala.concurrent.blocking
    blocking{ //Markiert diesen Codeteil als potentiell zeitverbrauchend. Erlaubt Thread-Allokation bei Bedarf.
      Thread sleep millis
    }
    val result = expression
    println(s"""Completing "$actionName" gave "$result"""")
    result
  }

  
}