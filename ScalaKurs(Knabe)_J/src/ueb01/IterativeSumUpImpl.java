package ueb01;

public class IterativeSumUpImpl implements SumUp {

	public long sumUp(int[] numbers, int fromIndex) {
		int sum = 0;
		for (int i = fromIndex; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}
}
