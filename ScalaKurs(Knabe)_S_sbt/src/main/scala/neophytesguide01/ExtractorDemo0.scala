package neophytesguide01

object ExtractorDemo0 {
  def main(args: Array[String]): Unit = {
    val userList = List(User("Adam", "Riese", 9), User("Eva", "Green", 3), User("Harry", "Potter", 1))
    
	println(advance(userList))
	println(advance(userList.tail))
	println(advance(userList.tail.tail))
  }
  
  case class User(firstName: String, lastName: String, score: Int)
  
  def advance(xs: List[User]) = xs match {
  	case User(_, _, score1) :: User(_, _, score2) :: _ => score1 - score2
  	case _ => 0
  }
}