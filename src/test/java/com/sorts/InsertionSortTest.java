package src.test.java.com.sorts;


import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.sorts.InsertionSort;

public class InsertionSortTest {

  @Test
  public void insertionSortTest() {
    InsertionSort insertionSort = new InsertionSort();
    
    //Test with random positive integers and zero
    Integer[] unsortedInt = new Integer[]{0, 5, 9, 2, 1, 3, 4, 8, 6, 7};
    Integer[] sortedInt = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assert.assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));

    //Test with random positive integers
    unsortedInt = new Integer[]{5, 9, 2, 1, 3, 4, 8, 6, 7};
    sortedInt = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    Assert.assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    //Worst case time complexity
    unsortedInt = new Integer[]{5,4,3,2,1,0};
    sortedInt = new Integer[]{0, 1, 2, 3, 4, 5};
    Assert.assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    //Testing on negative number with worst case
    unsortedInt = new Integer[]{-1,-2,-3,-4,-5};
    sortedInt = new Integer[]{-5,-4,-3,-2,-1};
    Assert.assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    //Random combination of positive, negative integers and zero
    unsortedInt = new Integer[]{-1,-5,-10,-990,990,1010,0};
    sortedInt = new Integer[]{-990,-10,-5,-1,0,990,1010};
    Assert.assertArrayEquals(sortedInt, insertionSort.sort(unsortedInt));
    
    //ASCII sort
    Character[] unsortedChar = new Character[]{'f', 'h', 'c', 'a', 'b', 'd', 'g', 'e'};
    Character[] sortedChar = new Character[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    Assert.assertArrayEquals(sortedChar, insertionSort.sort(unsortedChar));
  }
}
