package codercolor

import org.junit.Assert._
import org.junit.Test

/**JUnit4-Testtreiber für das zu erstellende Coder-Objekt.
 * @author Christoph Knabe 2011-12-10*/
class CoderTest {    
  
  @Test def codeByLoop(){
    _testEncodingAndDecoding(Coders.byLoop)   
  }
  
  @Test def codeByFold(){
    _testEncodingAndDecoding(Coders.byFold)   
  }
  
  @Test def codeByMap(){
    _testEncodingAndDecoding(Coders.byMap)   
  }
  
  private def _testEncodingAndDecoding(stringCoder: StringCoder){
    val plus3 = (c: Char) => (c+3).asInstanceOf[Char]
    assertEquals("KDOOR", stringCoder("HALLO", plus3))
    val allX: Char => Char = c => 'X'
    assertEquals("XXXXX", stringCoder("HALLO", allX))
    val minus3 = (c: Char) => (c-3).asInstanceOf[Char]
    assertEquals("HALLO", stringCoder(stringCoder("HALLO", plus3),minus3))
  }
  
}
