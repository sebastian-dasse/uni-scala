package methfunc

object Power {    

  def squareMultiplyMethod(b: Int, p: Int): Int = {
    if (p == 0) 1
    else if (p % 2 == 1) b * squareMultiplyMethod(b, p-1)
    else {
      val x = squareMultiplyMethod(b, p/2)
      x * x
    }
  }
  
  val squareMultiplyFunction = squareMultiplyMethod _
  
//  val squareMultiplyFunction = squareMultiplyMethod(_, _)
  
//  val squareMultiplyFunction: (Int, Int) => Int = squareMultiplyMethod(_, _)
  
//  val squareMultiplyFunction: (Int, Int) => Int = (b, p) => squareMultiplyMethod(b, p)
  
  
//  val squareMultiplyFunction: (Int, Int) => Int = (b, p) => {
//    if (p == 0) 1
//    else if (p % 2 == 1) b * squareMultiplyFunction(b, p-1)
//    else {
//      val x = squareMultiplyFunction(b, p/2)
//      x * x
//    }
//  }
  
}
