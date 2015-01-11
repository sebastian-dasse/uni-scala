package codercolor

case class Color(val red: Int, val green: Int, val blue: Int) {
  require(isValid(red) && isValid(green) && isValid(blue))
  def isValid(col: Int): Boolean = 0 <= col && col <= 255
}

object Color {
	def stringize(color: Color): String = color match {
	  case Color(255, 0, 0) => "Red"
	  case Color(r, 0, 0) => "Red(" + r + ")"
	  case Color(0, 255, 0) => "Green"
	  case Color(0, g, 0) => "Green(" + g + ")"
	  case Color(0, 0, 255) => "Blue"
	  case Color(0, 0, b) => "Blue(" + b + ")"
//	  case Color(r, g, b) => "Color(%s,%s,%s)".format(r ,g ,b)
	  case _ => color toString
	}
}
