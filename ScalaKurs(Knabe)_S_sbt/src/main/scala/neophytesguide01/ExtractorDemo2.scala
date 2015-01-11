package neophytesguide01

object ExtractorDemo2 {
  def main(args: Array[String]): Unit = {
    val user1: User = new FreeUser("Adam", 9, 0.6)
  	val user2: User = new FreeUser("Eva", 3, 0.9)
    val user3: User = new PremiumUser("Kain", 1)
    
    def greet(user: User): String = user match {
      case FreeUser(name, _, p) => 
        if (p > 0.75) "What can we do for you today, " + name + "?" else "Hello " + name
      case PremiumUser(name, _) => "Welcome back, dear " + name
    }
    
    println(greet(user1))
    println(greet(user2))
    println(greet(user3))
  }
  
  trait User {
    def name: String
    def score: Int
  }
  
  class FreeUser(val name: String, val score: Int, val upgradeProbability: Double) extends User
  object FreeUser {
    def unapply(user: FreeUser): Option[(String, Int, Double)] = Some((user.name, user.score, user.upgradeProbability))
  }
  
  class PremiumUser(val name: String, val score: Int) extends User
  object PremiumUser {
    def unapply(user: PremiumUser): Option[(String, Int)] = Some((user.name, user.score))
  }
}