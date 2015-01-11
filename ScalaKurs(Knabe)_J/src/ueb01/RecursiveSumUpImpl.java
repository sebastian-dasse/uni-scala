package ueb01;

public class RecursiveSumUpImpl implements SumUp {

	public long sumUp(int[] numbers, int fromIndex) {
		return fromIndex >= numbers.length 
				? 0 
				: numbers[fromIndex] + sumUp(numbers, fromIndex + 1);
	}
	
	//--- divide-and-conquer-version
//	public long sumUp(int[] numbers, int fromIndex) {
//		return sumUp(numbers, fromIndex, numbers.length-1);
//	}
//	
//	private long sumUp(int[] numbers, int fromIndex, int toIndex) {
//		if (fromIndex == toIndex) {
//			return numbers[fromIndex];
//		}
//		final int pivot = (fromIndex + toIndex) / 2;
//		return sumUp(numbers, fromIndex, pivot) 
//			 + sumUp(numbers, pivot + 1, toIndex);
//	}
}
