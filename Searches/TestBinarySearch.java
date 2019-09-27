package Searches;

import org.junit.Test;
import static org.junit.Assert.asertEquals;

public class UnitTestForBinarySearch {
	BinarySearch object = new BinarySearch();
	@Test
	public void test1() {
		int arr[] = {2,3,4,6,7,8,9};
		int actualResult = object.find(arr,5);
		assertEquals(-1,actualResult);
	}
	public void test2() {
		int arr[] = {2,3,4,5,6,7,8,9};
		int actualResult = object.find(arr,5);
		assertEquals(5,actualResult);
	}
	public void test3() {
		int arr[] = {2,3,4,6,7,8,9};
		int actualResult = object.find(arr,2);
		assertEquals(2,actualResult);
	}
	public void test4() {
		int arr[] = {2,3,4,6,7,8,9};
		int actualResult = object.find(arr,9);
		assertEquals(9,actualResult);
	}
	public void test5() {
		int arr[] = {2,3,4,6,7,8,9};
		int actualResult = object.find(arr,3);
		assertEquals(3,actualResult);
	}
}