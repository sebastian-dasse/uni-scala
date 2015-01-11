package intlist

/**
 * Contains service methods for class MyList.
 * 
 * @author Sebastian Dass&eacute;
 */
class ListServiceImpl extends ListService {
  
  /**Returns true, if the passed value is contained in the passed list.*/
  override def containedIn(value: Int, list: IntList): Boolean = 
    list.foldLeft((accu: Boolean, el: Int) => accu || el == value, false)
  
  /**Returns a List with all elements of list in the same order, but without all occurrences of value.*/
  override def removeFrom(value: Int, list: IntList): IntList = 
    list.foldRight((accu: IntList, el: Int) => {
      if (el == value) accu
      else accu prepend el
    }, EMPTY)
  
  /**Returns a List consisting of the elements of List head, followed by the elements of List tail.*/
  override def concatenate(head: IntList, tail: IntList): IntList = 
    head.foldRight((accu: IntList, el: Int) => accu prepend el, tail)
  
}