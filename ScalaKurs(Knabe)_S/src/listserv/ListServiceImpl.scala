package listserv

class ListServiceImpl extends ListService {

  def mergeSorted[T](a: List[T], b: List[T], before: (T,T)=>Boolean): List[T] = {
    
    def after(x: T, xs: List[T]): Boolean = xs == Nil || before(xs.head, x)
    
    def mergeSort(a: List[T], b: List[T], accu: List[T]): List[T] = (a, b) match {
      case (Nil, Nil) => accu
      case (a1 :: as, Nil) => {
        require(after(a1, as))
        mergeSort(as, Nil, a1 :: accu)
      }
      case (Nil, b1 :: bs) => {
	    require(after(b1, bs))
        mergeSort(Nil, bs, b1 :: accu)
      }
      case (a1 :: as, b1 :: bs) => {
    	  require(after(a1, as) && after(b1, bs))
        if (before(a1, b1))
          mergeSort(a, bs, b1 :: accu)
        else
          mergeSort(as, b, a1 :: accu)
      }
    }
    
    mergeSort(a.reverse, b.reverse, Nil)
  }
}