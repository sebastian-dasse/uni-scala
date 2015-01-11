package methfunc

import org.junit.Assert._
import org.junit.Test

/**JUnit4-Testtreiber für das zu erstellende Power-Objekt mit einer Methode und einer Funktion zum Potenzieren durch Quadrieren und Multiplizieren.
 * @author Christoph Knabe 2014-10-24*/
//Für Eclipse müssen Sie, falls noch nicht geschehen, JUnit 4 wie folgt dem Projekt hinzufügen:
//Navigator > Projektname > MausRechts > Properties > Java Build Path > Libraries > Add Library... > JUnit > JUnit 4
class PowerTest {    

  @Test def squareMultiplyMethod_2_13(){
    _testSquareMultiply(Power.squareMultiplyMethod(_,_), 2, 13)
  }
  
  @Test def squareMultiplyMethod_3_10(){
    _testSquareMultiply(Power.squareMultiplyMethod(_,_), 3, 10)
  }
  
  @Test def typeOfSquareMultiplyFunction(){
    val f = Power.squareMultiplyFunction
    assertTrue("Power.squareMultiplyFunction ist keine zweistellige Funktion im Typ Int", f.isInstanceOf[Function2[Int,Int,Int]])
  }
  
  @Test def squareMultiplyFunction_2_13(){
    val f: (Int,Int)=>Int = Power.squareMultiplyFunction
    _testSquareMultiply(f, 2, 13)
  }
  
  @Test def squareMultiplyFunction_3_10(){
    val f: (Int,Int)=>Int = Power.squareMultiplyFunction
    _testSquareMultiply(f, 2, 13)
  }
  
  private def _testSquareMultiply(f: (Int,Int)=>Int, base: Int, maxExponent: Int): Unit = {
    System.out.println()
    System.out.println("Potenzen von " + base)
    assertEquals(1, f(base,0))
    var expected = 1
    for(i<-1 to maxExponent){
      expected = expected*base
      val actual = f(base,i)
      assertEquals(expected, actual)
      System.out.println(actual)
    }
  }
    
}
