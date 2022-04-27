package assignent3;

import org.junit.Test;
import com.thealgorithms.sorts.BubbleSort;
import static org.junit.Assert.assertArrayEquals;

public class JUnitTestSort {

	@Test
	public void case1() { // Test if input array is null
		Integer[] inputArrs = null;
		Integer[] espectArrs = {};
		BubbleSort bubbleSort = new BubbleSort();
		assertArrayEquals(espectArrs, bubbleSort.sort(inputArrs));
	}

	@Test
	public void case2() { // Test if input array is empty
		Integer[] inputArrs = {};
		Integer[] espectArrs = {};
		BubbleSort bubbleSort = new BubbleSort();
		assertArrayEquals(espectArrs, bubbleSort.sort(inputArrs));
	}

	@Test
	public void case3() { // Test if input array contain all characters
		String[] inputArrs = { "c", "a", "e", "b", "d" };
		String[] espectArrs = { "a", "b", "c", "d", "e" };
		BubbleSort bubbleSort = new BubbleSort();
		assertArrayEquals(espectArrs, bubbleSort.sort(inputArrs));
	}

	@Test
	public void case4() { // Test if input array contain all numbers
		Integer[] inputArrs = { 4, 23, 6, 78, 1, 54, 231, 9, 12 };
		Integer[] espectArrs = { 1, 4, 6, 9, 12, 23, 54, 78, 231 };
		BubbleSort bubbleSort = new BubbleSort();
		assertArrayEquals(espectArrs, bubbleSort.sort(inputArrs));
	}

	@Test
	public void case5() { // Test if input array contain null item
		Integer[] inputArrs = { 4, 23, 6, 78, 1, 54, null, 9, 12 };
		Integer[] espectArrs = { 1, 4, 6, 9, 12, 23, 54, 78 };
		BubbleSort bubbleSort = new BubbleSort();
		assertArrayEquals(espectArrs, bubbleSort.sort(inputArrs));
	}
}