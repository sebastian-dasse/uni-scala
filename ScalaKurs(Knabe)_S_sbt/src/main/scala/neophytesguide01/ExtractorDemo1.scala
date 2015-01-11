package neophytesguide01

object ExtractorDemo1 {
  def main(args: Array[String]): Unit = {
    val user1: User = new FreeUser("Adam")
  	val user2: User = new PremiumUser("Eva")
    
    def greet(user: User): String = user match {
      case FreeUser(name) => "Hello " + name
      case PremiumUser(name) => "Welcome back, dear " + name
    }
    
    println(greet(user1))
    println(greet(user2))
  }
  
  trait User {
    def name: String
  }
  
  class FreeUser(val name: String) extends User
  object FreeUser {
    def unapply(user: FreeUser): Option[String] = Some(user.name)
  }
  
  class PremiumUser(val name: String) extends User
  object PremiumUser {
    def unapply(user: PremiumUser): Option[String] = Some(user.name)
  }
}