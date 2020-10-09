public class ExtendedCountingSort {

	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0) {
			Integer biggest = biggestInt(array, leftIndex, rightIndex);
			Integer smallest = smallestInt(array, leftIndex, rightIndex);

			Integer[] c = new Integer[biggest - smallest + 1];
			Integer[] b = new Integer[array.length];

			Arrays.fill(c, 0);

			for (int i = leftIndex; i <= rightIndex; i++) {
				c[array[i] - smallest] += 1;
			}

			for (int i = 1; i < c.length; i++) {
				c[i] += c[i - 1];
			}

			for (int i = rightIndex; i >= leftIndex; i--) {
				b[c[array[i] - smallest] - 1] = array[i];
				c[array[i] - smallest] -= 1;
			}

			for (int i = 0; i < b.length; i++) {
				array[i] = b[i];
			}
		}
	}

	private Integer smallestInt(Integer[] array, int leftIndex, int rightIndex) {
		int m = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex ; i++) {
			if (array[i] < m) { m = array[i]; }
		}

		return m;
	}

	private Integer biggestInt(Integer[] array, int leftIndex, int rightIndex) {
		int k = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex ; i++) {
			if (array[i] > k) { k = array[i]; }
		}

		return k;
	}

}
