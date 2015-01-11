package methfunc

import org.junit.Assert._
import org.junit.Test

class MathServiceTest {
  
  import MathServiceTest._
//  import MathServiceTest.{testee, fConst, }
  
  @Test def integrateFConstTo10() {
    val actual = testee.integrate(fConst, 10, 0.1)
	val expected = expectedFConstIntegral(10.0)
	assertEquals(expected, actual, expected*0.05)
  }

  @Test def integrateFLin1To5() {
	val actual = testee.integrate(fLin1, 5, 0.1)
	val expected = expectedFLin1Integral(5.0)
	assertEquals(expected, actual, expected*0.05)
  }

  @Test def integrateFQuadTo5() {
	val actual = testee.integrate(fQuad, 5, 0.1)
	val expected = expectedFQuadIntegral(5.0)
	assertEquals(expected, actual, expected*0.05)
  }

//  @Test def integralOfFLin1To1000() {
//	val integral = testee.integral(fLin1, 1.0)
//	var x: Int = 0
//	while (x < 1000) {
//	  val actual = integral(x)
//	  val expected = expectedFLin1Integral(x)
//	  assertEquals("Integral of fLin1(x)=x at " + x, expected, actual, expected*0.1+1.5)
//	  i = i + 1
//	}
//  }
  
}

object MathServiceTest {
  val testee = new MathServiceImpl();

  val fConst: Double => Double = x => 2.0
  val expectedFConstIntegral: Double => Double = x => 2*x
  val fLin1: Double => Double = x => x
  val expectedFLin1Integral: Double => Double = x => x*x/2
  val fQuad: Double => Double = x => 3*x*x + 5
  val expectedFQuadIntegral: Double => Double = x => x*x*x + 5*x;
}
