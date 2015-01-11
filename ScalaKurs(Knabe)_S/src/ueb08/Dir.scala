package ueb08

object Dir {

  def main(args: Array[String]) {
    if (args.length <= 0)
      throw new IllegalArgumentException("pass a valid path as the first and only argument")
    
    println("=== Listing all files in directory '" + args(0) + "' ===")
    new java.io.File(args(0)).list.foreach(println)
  }

}