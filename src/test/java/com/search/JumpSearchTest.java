package src.test.java.com.search;

import org.junit.Test;
import org.junit.Assert;

import src.main.java.com.search.JumpSearch;

public class JumpSearchTest {

	@Test
	public void testJumpSearch() {
		JumpSearch jumpSearch = new JumpSearch();
		
		Integer arr[] = {11, 15, 16, 29, 36, 40, 42, 52};
		int x = 36;
		int index = jumpSearch.findIndex(arr, x);
		Assert.assertEquals("Incorrect index", 4, index);

	    Integer arrTwo[] = {-210, -190, -180, -160, -130, -120, -100};
		x = -120;
		index = jumpSearch.findIndex(arrTwo, x);
		Assert.assertEquals("Incorrect index", 5, index);
		
		String arrString[] = {"101", "122", "136", "165", "225", "251","291"};
		String stringX = "122";
		index = jumpSearch.findIndex(arrString, stringX);
		Assert.assertEquals("Incorrect index", 1, index);
	}
}