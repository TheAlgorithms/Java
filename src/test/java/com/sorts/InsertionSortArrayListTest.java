package src.test.java.com.sorts;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import src.main.java.com.sorts.InsertionSortArrayList;

public class InsertionSortArrayListTest {
	
  @Test
  public void insertionSortArrayListTest() {
    InsertionSortArrayList insertionSortArrayList = new InsertionSortArrayList();

   ArrayList <Integer> unsortedInts = new ArrayList<>();
    
    unsortedInts.add(0);
    unsortedInts.add(3);
    unsortedInts.add(1);
    unsortedInts.add(2);
    
    ArrayList <Integer> sortedInts = new ArrayList<>();
    sortedInts.add(0);
    sortedInts.add(1);
    sortedInts.add(2);
    sortedInts.add(3);
    Assert.assertEquals(sortedInts, insertionSortArrayList.sort(unsortedInts));
    
    ArrayList <Character> unsortedChars = new ArrayList<>();
    unsortedChars.add('z');
    unsortedChars.add('a');
    unsortedChars.add('d');
    unsortedChars.add('c');
    unsortedChars.add('b');
    
    ArrayList <Character> sortedChars = new ArrayList<>();
    unsortedChars.add('a');
    unsortedChars.add('b');
    unsortedChars.add('c');
    unsortedChars.add('d');
    unsortedChars.add('z');

    Assert.assertEquals(sortedChars, insertionSortArrayList.sort(unsortedChars));
  }
}
