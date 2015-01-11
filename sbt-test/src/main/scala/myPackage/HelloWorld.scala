package myPackage

object HelloWorld {
  def main(args: Array[String]) = {
    println("Hello world!")
    new Greeter().sayHello
  }
}
