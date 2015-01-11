package intlist;

/**Contains service methods for class MyList.*/
trait ListService {
    
    /**Returns true, if the passed value is contained in the passed list.*/
    def containedIn(value: Int, list: IntList): Boolean
    
    /**Returns a List with all elements of list in the same order, but without all occurrences of value.*/
    def removeFrom(value: Int, list: IntList): IntList
    
    /**Returns a List consisting of the elements of List head, followed by the elements of List tail.*/
    def concatenate(head: IntList, tail: IntList): IntList

}
