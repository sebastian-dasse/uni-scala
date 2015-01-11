package neophytesguide01

object ExtractorDemo3 {
  def main(args: Array[String]): Unit = {
    val user1: User = new FreeUser("Adam", 9, 0.6)
  	val user2: User = new FreeUser("Eva", 3, 0.9)
    val user3: User = new PremiumUser("Kain", 1)
    
    def greet(user: User): Unit = user match {
      case freeUser @ PremiumCandidate() => initiateSpamProgram(freeUser)
      case premiumUser: PremiumUser => sendRegularNewsletter(premiumUser)
      case _ => 
    }
    
    def initiateSpamProgram(user: FreeUser) = {
      for (i <- 1 to 3) {
        println("What can we do for you today, " + user.name + "?")
      }
    }
    
    def sendRegularNewsletter(user: User) = {
      println("Welcome back, dear " + user.name)
    }
    
    greet(user1)
    greet(user2)
    greet(user3)
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
  
  object PremiumCandidate {
    def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75
  }
}