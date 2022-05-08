package com.thealgorithms.others;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.thealgorithms.datastructures.stacks.CalculateMaxOfMin;

public class CalculateMaxOfMinTest {
	@Test
	void testForOneElement() {
		int a[] = { 10, 20, 30, 50, 10, 70, 30 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 70);
	}

	@Test
	void testForTwoElements() {
		int a[] = { 5, 3, 2, 6, 3, 2, 6 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 6);
	}

	@Test
	void testForThreeElements() {
		int a[] = { 10, 10, 10, 10, 10, 10, 10 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 10);
	}

	@Test
	void testForFourElements() {
		int a[] = { 70, 60, 50, 40, 30, 20 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 70);
	}

	@Test
	void testForFiveElements() {
		int a[] = { 50 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 50);
	}

	@Test
	void testForSixElements() {
		int a[] = { 1, 4, 7, 9, 2, 4, 6 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == 9);
	}

	@Test
	void testForSevenElements() {
		int a[] = { -1, -5, -7, -9, -12, -14 };
		int k = CalculateMaxOfMin.calculateMaxOfMin(a);
		assertTrue(k == -1);
	}
}