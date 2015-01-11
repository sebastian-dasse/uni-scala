package ueb02

import org.junit.Assert.assertEquals
import org.junit.Test

class ListTest {

  val _empty = null
  val _list = List(1, List(2, List(3, null)))

  @Test
  def prependValueToEmptyList() {
    val actual = prepend(9, _empty);
    assertEquals("9", actual.toString)
  }

  @Test 
  def attachValueToEmptyList() {
    val actual = attach(9, _empty);
    assertEquals("9", actual.toString)
  }

  @Test 
  def prependValueToListWithElements() {
    val actual = prepend(9, _list);
    assertEquals("9 1 2 3", actual.toString)
  }

  @Test 
  def attachValueToListWithElements() {
    val actual = attach(9, _list);
    assertEquals("1 2 3 9", actual.toString)
  }

  /**
   * Prepends the given value to the fron of the given list.
   */
  def prepend(value: Int, list: List): List = {
    new List(value, list)
  }

  /**
   * Attaches the given value to the end of the given list.
   */
  def attach(value: Int, list: List): List = list match {
    case null => List(value, null)
    case _    => prepend(list.head, attach(value, list.tail))
  }
}
