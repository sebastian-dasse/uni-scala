package futur

object ConsoleDemo {
  def main(args: Array[String]) {
//    val ln = io.Source.stdin.getLines.next
	
	val map = scala.collection.mutable.Map[String, Long]()
	for (action <- List("grind", "heat", "froth", "brew", "combine")) {
	  readLine(s"$action")
	  val start = System.currentTimeMillis
	  readLine
	  map(action) = System.currentTimeMillis - start
	}
    
    for (elem <- map) println(elem)
//    for (elem <- map.toSeq.sortWith(_._1 < _._1)) println(elem)
    
  }
}