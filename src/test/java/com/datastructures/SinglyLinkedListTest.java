package com.datastructures;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class SinglyLinkedListTest {

	 private SinglyLinkedList<Integer> MySinglyLinkedList = new SinglyLinkedList<>();
	 
	    @Test
	    public void emptyTest() {
	        Assertions.assertEquals(0, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	    }
	 
	    @Test
	    public void addTest() {
	        MySinglyLinkedList.add(1);
	        Assertions.assertEquals(1, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.add(42);
	        Assertions.assertEquals(2, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.add(2);
	        MySinglyLinkedList.add(3);
	        Assertions.assertEquals(4, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	    }
	    
	    @Test
	    public void removeTest() {
	        MySinglyLinkedList.add(1);
	        MySinglyLinkedList.add(2);
	        MySinglyLinkedList.add(3);
	        MySinglyLinkedList.add(4);
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.remove(3);
	        Assertions.assertEquals(3, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.remove(0);
	        Assertions.assertEquals(2, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.remove(0);
	        Assertions.assertEquals(1, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	 
	        MySinglyLinkedList.remove(0);
	        Assertions.assertEquals(0, MySinglyLinkedList.size());
	        MySinglyLinkedList.printContent();
	    }
	 

 
}