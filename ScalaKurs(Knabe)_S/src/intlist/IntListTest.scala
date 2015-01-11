package intlist

import org.junit.Assert._
import org.junit.Test


/**JUnit4 test driver for testing the class IntList and the implementation ListServiceImpl for trait ListService*/
class IntListTest {

    /**An instance of the ListService, which has to be developed by the students.*/
    val testee: ListService = new ListServiceImpl    
    
    @Test def list1(){
        assertEquals("", EMPTY.toString)
        assertEquals("", EMPTY.toStringRF)
        assertEquals("", EMPTY.toStringEffizient)
        val list1 = EMPTY.prepend(1)
        assertEquals("1", list1.toString.trim)
        assertEquals("1", list1.toStringRF.trim)
        assertEquals("1", list1.toStringEffizient.trim)
    }
    @Test def list123(){
        val list123 = EMPTY.prepend(3).prepend(2).prepend(1)
        assertEquals("1 2 3", list123.toString.trim)
        assertEquals("1 2 3", list123.toStringRF.trim)
        assertEquals("1 2 3", list123.toStringEffizient.trim)
    }
    
    @Test def folds(){
        val list123 = EMPTY.prepend(3).prepend(2).prepend(1)
        val op = (preResult: Int, value: Int) => 2*preResult+value
        val foldLeftResult = list123.foldLeft(op, 0)
        assertEquals(11, foldLeftResult)
        val foldRightResult = list123.foldRight(op, 0)
        assertEquals(17, foldRightResult)
    }
    
    @Test def testContainedIn(){
        val list123 = EMPTY.prepend(3).prepend(2).prepend(1)
        assertEquals(false, testee.containedIn(0, list123))
        assertEquals(true, testee.containedIn(1, list123))
        assertEquals(true, testee.containedIn(2, list123))
        assertEquals(true, testee.containedIn(3, list123))
        assertEquals(false, testee.containedIn(4, list123))
    }

    @Test def testRemoveFrom(){
        val list1231 = EMPTY.prepend(1).prepend(3).prepend(2).prepend(1);
        assertEquals("1 2 3 1", testee.removeFrom(0, list1231).toString.trim)
        assertEquals("2 3", testee.removeFrom(1, list1231).toString.trim)
        assertEquals("1 3 1", testee.removeFrom(2, list1231).toString.trim)
        assertEquals("1 2 1", testee.removeFrom(3, list1231).toString.trim)
        assertEquals("1 2 3 1", testee.removeFrom(4, list1231).toString.trim)
    }
    
    @Test def testConcatenate(){
        val list123 = EMPTY.prepend(3).prepend(2).prepend(1)
        val list456 = EMPTY.prepend(6).prepend(5).prepend(4)
        assertEquals("1 2 3", testee.concatenate(EMPTY, list123).toString.trim)
        assertEquals("1 2 3", testee.concatenate(list123, EMPTY).toString.trim)
        assertEquals("1 2 3 4 5 6", testee.concatenate(list123, list456).toString.trim)
        assertEquals("4 5 6 1 2 3", testee.concatenate(list456, list123).toString.trim)
    }
    
    //Lehrmaterial:
    
    /**Liefert eine Liste mit den natürlichen Zahlen von low bis high.*/
    def range(low: Int, high: Int): IntList = {   
      if(low>high) EMPTY
	  else range(low+1, high).prepend(low)
    }
    
    @Test def testRange(){
      val result = range(1, 6)
      assertEquals("1 2 3 4 5 6", result.toString.trim)
    }
    
    /**Liefert die Anzahl von Int-Zahlen in dieser Liste.*/
	def len(list: IntList): Int = { 
		if(list isEmtpy) 0 else 1 + len(list.tail)    
	}
	
	@Test def testLen(){
      val list1to6 = range(1, 6)
      val result = len(list1to6)
      assertEquals(6, result)
	}
	
	def sum(list: IntList): Int = { 
      if(list isEmtpy) 0 else list.head + sum(list.tail)
    }
	
	@Test def testSum(){
      val list1to10 = range(1, 10)
      val result = sum(list1to10)
      assertEquals(55, result)
	}
	
	def toString(list: IntList): String = {
      if(list isEmtpy) ""
      //else if(list.tail isEmtpy) list.head.toString
      else list.head + " " + toString(list.tail)
    }
	
	@Test def testToString(){
      assertEquals("", toString(EMPTY))
      assertEquals("1", toString(EMPTY.prepend(1)).trim)
      val list1to6 = range(1, 6)
      val result = toString(list1to6)
      assertEquals("1 2 3 4 5 6", result.trim)
	}    
	
	@Test def testPrepend(){
      val numbers = EMPTY.prepend(7).prepend(5).prepend(3).prepend(2)
      assertEquals("2 3 5 7", numbers.toString.trim)
	}  
	
	def append(list: IntList, v: Int): IntList = {
      if(list isEmtpy) EMPTY.prepend(v)
      else append(list.tail, v).prepend(list.head)
    }
	
	@Test def testAppend(){
      val numbers = append(append(append(append(EMPTY, 2), 3), 5), 7)
      assertEquals("2 3 5 7", numbers.toString.trim)
	}  
	
	scala.List

}
