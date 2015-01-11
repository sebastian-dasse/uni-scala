/**All names defined in this package object are directly in the package's namespace.*/
package object codercolor {
  
/**Type for functions, which encode the String s by applying the Char coder for each character.*/
type StringCoder = (String, Char=>Char) => String
  
}

