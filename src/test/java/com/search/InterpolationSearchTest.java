package com.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpolationSearchTest {

	@Test
	public void testInterpolationSearch() {
		InterpolationSearch interpolationSearch = new InterpolationSearch();
		
		Integer arr[] = {10, 12, 13, 16, 18, 19, 21};
		int x = 18;
		int index = interpolationSearch.findIndex(arr, x);
		assertEquals(4, index);

		Integer arrTwo[] = {-210, -190, -180, -160, -130, -120, -100};
		x = -190;
		index = interpolationSearch.findIndex(arrTwo, x);
		assertEquals(1, index);
		
		String arrString[] = {"10", "12", "13", "16", "22", "25","29"};
		String stringX = "13";
		index = interpolationSearch.findIndex(arrString, stringX);
		assertEquals(2, index);
	}
}