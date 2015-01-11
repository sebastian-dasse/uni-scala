package codercolor

import org.junit.Assert._
import org.junit.Test

/**JUnit4-Testtreiber für das zu erstellende Color-Objekt.
 * @author Christoph Knabe 2011-12-10*/
class ColorTest {    
  
  @Test def constructorAndAttributes(){
    val c = new Color(10,20,30) //Call constructor of class Color.
    assertEquals(10, c.red)
    assertEquals(20, c.green)
    assertEquals(30, c.blue)
  }
  
  @Test def applyMethodAndAttributes(){
    val c = Color(10,20,30) //Call apply method of object Color.
    assertEquals(10, c.red)
    assertEquals(20, c.green)
    assertEquals(30, c.blue)
  }

  @Test def testToString(){
    assertEquals("Color(255,0,0)", Color(255,0,0) toString)
    assertEquals("Color(0,255,0)", Color(0,255,0) toString)
    assertEquals("Color(0,0,255)", Color(0,0,255) toString)
    assertEquals("Color(100,0,0)", Color(100,0,0) toString)
    assertEquals("Color(0,200,0)", Color(0,200,0) toString)
    assertEquals("Color(0,0,50)", Color(0,0,50) toString)
    assertEquals("Color(0,1,2)", Color(0,1,2) toString)
    assertEquals("Color(1,0,2)", Color(1,0,2) toString)
    assertEquals("Color(1,2,0)", Color(1,2,0) toString)
  }    
  
  @Test def stringize(){
    assertEquals("Red", Color stringize Color(255,0,0))
    assertEquals("Green", Color stringize Color(0,255,0))
    assertEquals("Blue", Color stringize Color(0,0,255))
    assertEquals("Red(100)", Color stringize Color(100,0,0))
    assertEquals("Green(200)", Color stringize Color(0,200,0))
    assertEquals("Blue(50)", Color stringize Color(0,0,50))
    assertEquals("Color(0,1,2)", Color stringize Color(0,1,2))
    assertEquals("Color(1,0,2)", Color stringize Color(1,0,2))
    assertEquals("Color(1,2,0)", Color stringize Color(1,2,0))
  }    
  
  @Test def require(){
    _expectFailure(Color(-1,0,0))
    _expectFailure(Color(0,-1,0))
    _expectFailure(Color(0,0,-1))
    _expectFailure(Color(256,0,0))
    _expectFailure(Color(0,256,0))
    _expectFailure(Color(0,0,256))
  }
  
  /**Prüft, ob der übergebene Ausdruck color bei Berechnung eine IllegalArgumentException wirft.
   * color: => Color
   * ist ein so genannter Call-by-name-Parameter. Dieser wird nicht beim Aufruf evaluiert, 
   * sondern bei jeder Verwendung innerhalb der Methode.
  */
  private def _expectFailure(color: =>Color) = try{
    color
    fail("expected: IllegalArgumentException for " + color)
  }catch{
    case expected: IllegalArgumentException => 
  }
}
