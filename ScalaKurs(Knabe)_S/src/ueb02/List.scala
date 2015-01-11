package ueb02

/**
 * Einfach vorwärts verkettete Liste von Ganzzahlen à la LISP.
 * Eine Liste mit den Elementen 1, 2 und 3 kann gebildet werden durch new List(1, new List(2, new List(3,null)))
 *
 * Der Konstruktor entspricht dem CONS-Operation von LISP.
 *
 * @param head	Inhalt eines Listenknotens
 * @param tail	Vorwärtsreferenz zum nächsten Listenknoten. Das Listenende wird durch null markiert.
 */
class List(val head: Int, val tail: List) {

  /**Liefert die String-Darstellung einer aus Paaren gebildeten, über die Referenz tail vorwärts verketteten Liste.*/
  override def toString(): String = tail match {
    case null => head.toString
    case _ 	  => head.toString + " " + tail.toString
  }
}

object List {
  def apply(head: Int, tail: List) = new List(head, tail)
}
