@@ -0,0 +1,65 @@
package com.sorting.quick;

import static com.sorting.insert.SortUtils.arrayToString;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuickSortTest {

  // đầu vào đã được sắp xếp từ nhỏ tới lớn
  @Test
	public void testQuickSort() {
		int [] input=new int[]{1,2,3,4};
		QuickSort sort = new QuickSort();
		int[] sortedArray = sort.sort(input);

		assertEquals("1:2:3:4:", arrayToString(sortedArray));
	}

  // số lớn nhất đứng đầu tiên
  @Test
	public void testQuickSort() {
		int [] input=new int[]{7,4,2,5};
		QuickSort sort = new QuickSort();
		int[] sortedArray = sort.sort(input);

		assertEquals("2:4:5:7:", arrayToString(sortedArray));
	}



  // đầu vào đã được sắp xếp từ lớn tới bé
  @Test
	public void testQuickSort() {
		int [] input=new int[]{10,6,3,2};
		QuickSort sort = new QuickSort();
		int[] sortedArray = sort.sort(input);

		assertEquals("2:3:6:10:", arrayToString(sortedArray));
	}

  // đầu vào có những giá trị bằng nhau
  @Test
	public void testQuickSort() {
		int [] input=new int[]{4,6,3,3};
		QuickSort sort = new QuickSort();
		int[] sortedArray = sort.sort(input);

		assertEquals("3:3:4:6:", arrayToString(sortedArray));
	}



  // sắp xếp với chữ cái
  @Test
	public void testQuickSort() {
		int [] input=new int[]{a,e,h,g};
		QuickSort sort = new QuickSort();
		int[] sortedArray = sort.sort(input);

		assertEquals("a:e:g:h:", arrayToString(sortedArray));
	}


}
