package ueb02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ListTest {

	private static final List _empty = null;
	private static final List _list = new List(1, new List(2, new List(3, null)));

	@Test
	public void prependValueToEmptyList() {
		final List actual = prepend(9, _empty);
		assertEquals(new List(9, null), actual);
		assertEquals("9", actual.toString());
	}

	@Test
	public void attachValueToEmptyList() {
		final List actual = attach(9, null);
		assertEquals(new List(9, null), actual);
		assertEquals("9", actual.toString());
	}

	@Test
	public void prependValueToListWithElements() {
		final List actual = prepend(9, _list);
		assertEquals(new List(9, _list), actual);
		assertEquals("9 1 2 3", actual.toString());
	}
	@Test
	public void attachValueToListWithElements() {
		final List actual = attach(9, _list);
		assertEquals(new List(1, new List(2, new List(3, new List(9, null)))), actual);
		assertEquals("1 2 3 9", actual.toString());
	}

	/**
	 * Prepends the given value to the fron of the given list.
	 */
	private List prepend(final int value, final List list) {
		return new List(value, list);
	}

	/**
	 * Attaches the given value to the end of the given list.
	 */
	private List attach(final int value, final List list) {
		return list == null 
				? new List(value, null) 
				: prepend(list.head, attach(value, list.tail));
	}
}
