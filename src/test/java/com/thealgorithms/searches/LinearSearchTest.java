package com.thealgorithms.searches;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinearSearchTest {

	LinearSearch search = new LinearSearch();
	
	@Test
	public void searchNumberTestNonDuplicate() {
		Integer [] integers = {51,23,45,89,65,32};
		Integer intTosearch= 51;
		int expResult= 0;
		int actResult = search.find(integers, intTosearch);
		assertEquals(actResult, expResult);
	}
	
	
	@Test
	public void searchNumberTestDuplicate() {
		Integer [] integers = {45,58,67,67,20,32};
		Integer intTosearch= 67;
		int expResult= 2;
		int actResult = search.find(integers, intTosearch);
		assertEquals(actResult, expResult);
	}

}
