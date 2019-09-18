import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddStringsTest {
@Test
public void testcase1(){
		Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17}
		InterpolationSearch search = new InterpolationSearch();
		int atIndex = search.find(array, 1);
		assertTrue(atIndex == 1);
}
@Test
public void testcase2(){
		Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17}
		InterpolationSearch search = new InterpolationSearch();
		int atIndex = search.find(array, 3);
		assertTrue(atIndex == 2);
}
@Test
public void testcase3(){
		Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17}
		InterpolationSearch search = new InterpolationSearch();
		int atIndex = search.find(array, 9);
		assertTrue(atIndex == 5);
}
@Test
public void testcase4(){
		Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17}
		InterpolationSearch search = new InterpolationSearch();
		int atIndex = search.find(array, 15);
		assertTrue(atIndex == 8);
}
@Test
public void testcase5(){
		Integer[] array = {1, 3, 5, 7, 9, 11, 13, 15, 17}
		InterpolationSearch search = new InterpolationSearch();
		int atIndex = search.find(array, 17);
		assertTrue(atIndex == 9);
}
}
