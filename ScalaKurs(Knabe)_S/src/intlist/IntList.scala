package intlist;

import java.util.NoSuchElementException;


object EMPTY extends IntList(-1, null) {  
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def isEmtpy = true
}

/**An immutable linked list of integer numbers. The empty list is represented by the object EMPTY.*/
class IntList(    
    /**The first immutable element value of this IntList*/
    _head : Int, 
    /**An immutable reference to all other elements of this IntList*/
    _tail : IntList
){
    def head = _head
    def tail = _tail
    
    /**A list with a head is never empty.*/
    def isEmtpy = false
    
    /**An IntList with the passed value as its first element, followed by all elements of this IntList*/
    def prepend(value: Int) = new IntList(value, this)
    
    /**A general operation alias to integrate an Int value into a RESULT*/
    type Operation[RESULT] = (RESULT, Int) => RESULT
    
    /**Returns the accumulated result of applying the operation op to each value in this list from left to right,
     * and the previous result, starting with the initial value.*/
    def foldLeft[RESULT](op: Operation[RESULT], initial: RESULT): RESULT = {
        var result = initial
        var actual = this
        while(!actual.isEmtpy){
            result = op(result, actual.head)
            actual = actual.tail
        }
        result
    }
    
    /**Returns a String representation of this IntList's content in the format of for example "1 2 3".*/
    override def toString: String = {
        val result = new StringBuilder
        foldLeft(
            (accu: StringBuilder, value: Int) 
            => accu append value append ' '
            , result
        );
        result.toString
    }
    
    //Lehrmaterial:
    
    /**Returns the accumulated result of applying the operation op to each value in this list from right to left,
     * and the previous result, starting with the initial value.
     * @throws java.lang.StackOverflowError This List is too long.*/
    def foldRight[R](op: Operation[R], initial: R): R = {
        if(isEmtpy) initial
        else op(tail.foldRight(op, initial), head);
    }
    
    def toStringRF: String =
      foldRight((accu: String, value) => value + " " + accu, "")
	
    /**Returns the sum of all elements of this list. If the list is empty, 0 is returned.*/
	def sum: Int = foldRight((x: Int, y: Int) => x+y, 0)
	
	def toStringEffizient: String = {
        val result = new StringBuilder
        var actual = this
        while(!actual.isEmtpy){
            result append actual.head append " "
            actual = actual.tail
        }
        result.toString
    }

}
