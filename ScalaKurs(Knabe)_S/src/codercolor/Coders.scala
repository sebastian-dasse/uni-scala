package codercolor

object Coders {

  val byLoop: StringCoder = (string, coder) => {
    val result = new StringBuilder
    var i: Int = 0
	while (i < string.length) {
	  result append coder(string.charAt(i))
	  i += 1
	}
    result toString
  }
  
  val byFold: StringCoder = (string, coder) => 
    string.foldLeft(new StringBuilder)((accu: StringBuilder, value: Char) => accu append coder(value)) toString
  
  val byMap: StringCoder = (string, coder) => string.map(coder)
  
}
