// Author: Michael Gentile
// Date: 9 May 2018
// Miami University CSE 321
// Singly linked list testing file. 
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SinglyLinkedTest {
    private SinglyLinkedList singleLinkedList;

    @Before
    public void setUp() throws Exception {
        singleLinkedList = new SinglyLinkedList();
    }
    public void atNthSetup() {
    	this.singleLinkedList.insertHead(5);
    	this.singleLinkedList.insertHead(4);
    	this.singleLinkedList.insertHead(3);
    	this.singleLinkedList.insertHead(2);
    	this.singleLinkedList.insertHead(1);
    }
    /*
     * 1. [1,2,3,4,5,6,9,10]
	 *2. [1,2,3,4,5,6,7,8]
	 *3. [7,8,6,9,10]
	 *4. [1,2,3,11]
	 *5. [7,8,6,7]
	 *6. [8,6,7,8]
	 *7. [6,7,8,6]
	 *8. [1,2,12]
	 *
	 */
    //this tests size of two
    @Test
    public void getSizeTestOne() {
        //this tests size of two
        this.singleLinkedList.insertHead(1);
        this.singleLinkedList.insertHead(2);
        assertEquals(this.singleLinkedList.getSize(), 2);
    }
    //this tests n many sized list
    @Test
    public void getSizeTestTwoandThreeAndFiveAndSixAndSeven() {
        this.singleLinkedList.insertHead(1);
        this.singleLinkedList.insertHead(2);
        this.singleLinkedList.insertHead(3);
        assertEquals(this.singleLinkedList.getSize(), 3);
    }
	
    // this tests a one sized list. 
    @Test
    public void getSizeTestFour() {
        this.singleLinkedList.insertHead(1);
        assertEquals(this.singleLinkedList.getSize(), 1);
    }
	
    // this test a zero sized list
    @Test
    public void getSizeTestEight() {
        assertEquals(this.singleLinkedList.getSize(), 0);
    }
    /*
		insertAtNth
		1.[1,3,4,5,6,7] // this test covers 3, 4, 5, 6
		2.[1,3,4,5,8,9] // insert at the front.
		3.[6,7,5,8,9]
		4.[5,6,7,5]
		5.[7,5,6,7]
		6. [6,7,5,6]
		7. [1,2]	//check for bad index

     */
	
     // this tests an n many list having an element added. 
    @Test
    public void insertAtNthTestOne() {
        this.atNthSetup();
        //list is currently 1 2 3 4 5
        this.singleLinkedList.insertAtNth(10, 2);
        assertEquals(this.singleLinkedList.display(), "1 2 3 10 4 5 ");
    }
	
    // this tests addition to the beginning of a list. 
    @Test
    public void insertAtNthTestTwo() {
    	this.atNthSetup();
        //list is currently 1 2 3 4 5
        this.singleLinkedList.insertAtNth(10, 0);
        assertEquals(this.singleLinkedList.display(), "10 1 2 3 4 5 ");
    }

    //this tests an n many list and going beyond the bounds of the list. 
    @Test(expected = IndexOutOfBoundsException.class)
    public void insertAtNthTestSeven() {
        this.atNthSetup();
        this.singleLinkedList.insertAtNth(10, 10);
    }
	
    // this tests the insertHead method's only node. 
    @Test
    public void testInsertHead() {
    	this.singleLinkedList.insertHead(1);
    	 assertEquals(this.singleLinkedList.display(), "1 ");
    }
	
    // this tests deletehead's only node. 	
    @Test
    public void testDeleteHead() {
    	this.singleLinkedList.insertHead(1);
   	 	assertEquals(this.singleLinkedList.display(), "1 ");
   	 	this.singleLinkedList.deleteHead();
   	 	assertEquals(this.singleLinkedList.display(), "");
    }
}
