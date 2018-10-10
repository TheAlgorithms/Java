package src.test.java.com.search;

import org.junit.Test;
import org.junit.Assert;
import src.main.java.com.search.LinearSearch;

public class LinearSearchTest {

	@Test
	public void testSearch() {
		int[] arr = {1, 2, 4, 5};
		
		Assert.assertEquals(0, LinearSearch.search(1, arr));
		Assert.assertEquals(1, LinearSearch.search(2, arr));
		Assert.assertEquals(3, LinearSearch.search(5, arr));
		
		Assert.assertEquals(-1, LinearSearch.search(0, arr));
		Assert.assertEquals(-1, LinearSearch.search(3, arr));
		Assert.assertEquals(-1, LinearSearch.search(6, arr));
		Assert.assertEquals(-1, LinearSearch.search(-5, arr));		
	}
}
