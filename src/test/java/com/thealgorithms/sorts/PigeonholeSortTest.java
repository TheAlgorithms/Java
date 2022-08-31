package com.thealgorithms.sorts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PigeonholeSortTest {
	
	@Test
	void PigeonholeSortNonDuplicateTest() {
		PigeonholeSort pigeonholeSort = new PigeonholeSort();
		Integer[] expectedArray = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Integer[] arr = { 3, 7, 4, 8, 6, 2, 1, 5 };

        pigeonholeSort.sort(arr);

        assertArrayEquals(expectedArray, arr);
            
        }
	
	@Test
	void PigeonholeSortDuplicateTest() {
		PigeonholeSort pigeonholeSort = new PigeonholeSort();
		Integer[] expectedArray = { 2, 3, 4, 6, 7, 8, 8};
        Integer[] arr = { 8, 3, 2, 7, 4, 6, 8 };

        pigeonholeSort.sort(arr);

        assertArrayEquals(expectedArray, arr);
            
        }
	
	}

	



