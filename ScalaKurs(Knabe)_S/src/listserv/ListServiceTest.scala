package listserv

import org.junit.Assert._
import org.junit.Test
import scala.io.Source
import scala.runtime.RichInt

/**JUnit4-Testtreiber für die zu erstellende Implementierung des Traits ListService.
 * @author Christoph Knabe 2011-12-29*/
//Für Eclipse müssen Sie in der Datei .classpath hinter dem Scala- und Java-Container folgenden Eintrag ergänzen:
//   <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
class ListServiceTest {    
  
  val testee = new ListServiceImpl
  
  @Test def aNil(){
    val a = Nil
    val b = List(2, 4, 6, 8, 10)
    val result = testee.mergeSorted[Int](a, b, _<_)
    assertEquals(b, result)
  }
  
  @Test def bNil(){
    val a = List(1, 3, 5, 7, 9)
    val b = Nil
    val result = testee.mergeSorted[Int](a, b, _<_)
    assertEquals(a, result)
  }
  
  @Test def aUnsorted(){
    val a = List(3, 5, 7, 9, 8)
    val b = List(2, 4, 6, 8, 10)
    try{
      testee.mergeSorted[Int](a, b, _<_)
      fail("expected: IllegalArgumentException")
    }catch{
      case expected: IllegalArgumentException =>
    }
  }
  
  @Test def bUnsorted(){
    val a = List(1, 3, 5, 7, 9)
    val b = List(2, 1, 6, 8, 10)
    try{
      testee.mergeSorted[Int](a, b, _<_)
      fail("expected: IllegalArgumentException")
    }catch{
      case expected: IllegalArgumentException =>
    }
  }
  
  @Test def sameLengthSorted(){
    val a = List(1, 3, 5, 7, 9)
    val b = List(2, 4, 6, 8, 10)
    val result = testee.mergeSorted[Int](a, b, _<_)
    assertEquals(List(1,2,3,4,5,6,7,8,9,10), result)
  }
  
  @Test def unsameLengthSorted(){
    val a = List(1, 3, 5)
    val b = List(2, 4, 6, 8, 10)
    val result = testee.mergeSorted[Int](a, b, _<_)
    assertEquals(List(1,2,3,4,5,6,8,10), result)
  }
  
  @Test def bUnsortedInTheMiddle(){
    val a = List(8, 9)
    val b = List(1, 2, 5, 1, 6, 7)
    try{
      testee.mergeSorted[Int](a, b, _<_)
      fail("expected: IllegalArgumentException")
    }catch{
      case expected: IllegalArgumentException =>
    }
  }
  
}
