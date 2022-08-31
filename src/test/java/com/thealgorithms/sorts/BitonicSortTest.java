package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitonicSortTest {

	@Test
	public void BitonicSortNonDuplicateTest() {
		int[] expectedArray = { 1, 2, 3, 4, 5, 6, 7, 8};
		int a[] = {3, 7, 4, 8, 6, 2, 1, 5};
        int up = 1;
        BitonicSort ob = new BitonicSort();
        ob.sort(a, a.length, up);
        assertArrayEquals(expectedArray, a);

	}
	
	@Test
	public void BitonicSortDuplicateTest() {
		int[] expectedArray = { 1, 2, 3, 3, 3, 5, 6, 8};
		int a[] = {3, 3, 3, 8, 6, 2, 1, 5};
        int up = 1;
        BitonicSort ob = new BitonicSort();
        ob.sort(a, a.length, up);
        assertArrayEquals(expectedArray, a);

	}

}