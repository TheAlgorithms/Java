package com.thealgorithms.others;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.thealgorithms.datastructures.stacks.CalculateMaxOfMin;

public class CalculateMaxOfMinTest {
	@Test
	void testForOneElement() {
		int a[] = { 10, 20, 30, 50, 10, 70, 30 };
		int b[] = { 70, 30, 20, 10, 10, 10, 10 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForTwoElements() {
		int a[] = { 5, 3, 2, 6, 3, 2, 6 };
		int b[] = { 6, 3, 2, 2, 2, 2, 2 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForThreeElements() {
		int a[] = { 10, 10, 10, 10, 10, 10, 10 };
		int b[] = { 10, 10, 10, 10, 10, 10, 10 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForFourElements() {
		int a[] = { 70, 60, 50, 40, 30, 20 };
		int b[] = { 70, 60, 50, 40, 30, 20 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForFiveElements() {
		int a[] = { 50 };
		int b[] = { 50 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForSixElements() {
		int a[] = { 1, 4, 7, 9, 2, 4, 6 };
		int b[] = { 9, 7, 4, 2, 2, 2, 1 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}

	@Test
	void testForSevenElements() {
		int a[] = { -1, -5, -7, -9, -12, -14 };
		int b[] = { -1, -5, -7, -9, -12, -14 };
		assertTrue(CalculateMaxOfMin.calculateMaxOfMin(a, b, a.length));
	}
}
