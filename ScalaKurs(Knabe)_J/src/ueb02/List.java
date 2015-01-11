package ueb02;


/**Einfach vorwärts verkettete Liste von Ganzzahlen à la LISP.
 * Eine Liste mit den Elementen 1, 2 und 3 kann gebildet werden durch new List(1, new List(2, new List(3,null)))*/
public class List {
    
    /**Inhalt eines Listenknotens*/
    public final int head;
    
    /**Vorwärtsreferenz zum nächsten Listenknoten. Das Listenende wird durch null markiert.*/
    public final List tail;
    
    /**Dieser Konstruktor entspricht der CONS-Operation von LISP.*/
    public List(final int head, final List tail){
        this.head = head;
        this.tail = tail;
    }
    
    /**Liefert die String-Darstellung einer aus Paaren gebildeten, über die Referenz tail vorwärts verketteten Liste.*/
    public String toString(){
        return Integer.toString(head) + (tail==null ? "" : " " + tail.toString());
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + head;
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		List other = (List) obj;
		if (head != other.head)
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}
    
}
