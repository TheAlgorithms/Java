package src.test.java.com.sorts;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.StoogeSort;

public class StoogeSortTest {

	@Test
	public void stoogeSortTest() {
		StoogeSort stoogesort = new StoogeSort();

		Integer unsortedArr[] = { 2, 4, 5, 3, 1 };
		Integer n = unsortedArr.length;
		Integer sortedArr[] = { 1, 2, 3, 4, 5 };
		Assert.assertArrayEquals(sortedArr, stoogesort.sort(unsortedArr, 0, n - 1));

		unsortedArr = new Integer[] { -22, -34, -25, -53, -11 };
		sortedArr = new Integer[] { -53, -34, -25, -22, -11 };
		Assert.assertArrayEquals(sortedArr, stoogesort.sort(unsortedArr, 0, n - 1));
		
		Character[] unsortedCharArr = new Character[] { 'a', 'r', 'd', 'k', 'p' };
		n = unsortedCharArr.length;
		Character[] sortedCharArr = new Character[] { 'a', 'd', 'k', 'p', 'r' };
		Assert.assertArrayEquals(sortedCharArr, stoogesort.sort(unsortedCharArr, 0, n - 1));
	}
}