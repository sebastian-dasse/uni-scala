package methfunc

import scala.annotation.tailrec

/** A Service for numerically integrating functions of double floating point numbers.*/
class MathServiceImpl {
  
  /** Computes the area between the X-axis and the graph of the function f from 0 to endX 
   * by summing up the rectangles beneath the graph with a width of incrementX. */
  def integrate(f: Double => Double, endX: Double, incrementX: Double): Double = {
	if (endX <= 0) 0 
	else incrementX * f(endX) + integrate(f, endX - incrementX, incrementX)
  }
	
//  //---- tail-recursive version
//  def integrate(f: Double => Double, endX: Double, incrementX: Double): Double = {
//	integrate(f, endX - incrementX, incrementX, 0)
//  }
//  
//  @tailrec private def integrate(f: Double => Double, endX: Double, incrementX: Double, sum: Double): Double = {
//    if (endX <= 0) sum 
//    else integrate(f, endX - incrementX, incrementX, sum + incrementX * f(endX))
//  }
    
  /** Returns a function, which is the integration function for f, computed by X-increments of incrementX. */
  val integral = (f: Double => Double, incrementX: Double) => integrate(f, _: Double, incrementX)
  
//  val integral: (Double => Double, Double) => (Double => Double) = (f, incrementX) => {
//	integrate(f, _, incrementX)
//  }

}