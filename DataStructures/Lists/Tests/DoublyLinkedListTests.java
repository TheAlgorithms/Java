/**
 * This class implements Input Space Partitioning JUnit tests
 * for the DoublyLinkedList class.
 * 
 * Please see the DoublyLinkedListTests.PDF file for information 
 * about the test inputs and results.
 * 
 * Note: All of the tests were performed using the descriptions 
 * provided in the original DoublyLinkedList class. 
 * 
 * @author Connor14
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTests {
	
	private DoublyLinkedList list = null;	
	
	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList();
	}
	
	// resets the list.
	public void reset() {
		list = new DoublyLinkedList();
	}

	@Test
	public void testInsertHead() {
		list.insertHead(1); //TestID: 1 - c1.b1, c2.b1, c3.b2
		assertEquals(1, list.size());
		assertEquals(1, list.getHead().value);
		assertEquals(1, list.getTail().value);
		
		reset();
		
		list.insertHead(-2);
		
		list.insertHead(-2); //TestID: 2 - c1.b2, c2.b2, c3.b1
		assertEquals(2, list.size());
		assertEquals(-2, list.getHead().value);
		assertEquals(-2, list.getTail().value);

		reset();
		
		list.insertHead(1);
		list.insertHead(0);
		
		list.insertHead(0); //TestID: 3 - c1.b3, c2.b3, c3.b1
		assertEquals(3, list.size());
		assertEquals(0, list.getHead().value);
		assertEquals(1, list.getTail().value);

	}

	// test 1 gives NullPointerException
	// tests 2 and 3 pass
	@Test
	public void testInsertTail() {		
		list.insertTail(3); //TestID: 1 - c1.b1, c2.b1, c3.b2
		assertEquals(1, list.size());
		assertEquals(3, list.getHead().value);
		assertEquals(3, list.getTail().value);
		
		reset();
		
		list.insertHead(-10);
		
		list.insertTail(-10); //TestID: 2 - c1.b2, c2.b2, c3.b1
		assertEquals(2, list.size());
		assertEquals(-10, list.getHead().value);
		assertEquals(-10, list.getTail().value);
		
		reset();
		
		list.insertHead(0);
		list.insertHead(2);	
		
		list.insertTail(0); //TestID: 3 - c1.b3, c2.b3, c3.b1
		assertEquals(3, list.size());
		assertEquals(2, list.getHead().value);
		assertEquals(0, list.getTail().value);
	}
	
	@Test
	public void testInsertOrdered() {
		list.insertOrdered(8); //TestID: 1 - c1.b1, c2.b1, c3.b2
		assertEquals(1, list.size());
		assertEquals(8, list.getHead().value);
		assertEquals(8, list.getTail().value);
		
		reset();
		
		list.insertHead(-19);
		
		list.insertOrdered(-19); //TestID: 2 - c1.b2, c2.b2, c3.b1
		assertEquals(2, list.size());
		assertEquals(-19, list.getHead().value);
		assertEquals(-19, list.getTail().value);
		
		reset();
		
		list.insertHead(-91);
		list.insertTail(0);
		
		list.insertOrdered(0); //TestID: 3 - c1.b3, c2.b3, c3.b1
		assertEquals(3, list.size());
		assertEquals(-91, list.getHead().value);
		assertEquals(0, list.getTail().value);
	}

	// test 1 gives NullPointerException
	// test 2 gives NullPointerException
	// test 3 runs, but has assertion error
	@Test
	public void testDeleteHead() {
		Link newHead0 = list.deleteHead(); //TestID: 1 - c1.b1, c2.b2
		assertEquals(null, newHead0);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(1);
		
		Link newHead1 = list.deleteHead(); //TestID: 2 - c1.b2, c2.b1
		assertEquals(null, newHead1);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(1);
		list.insertTail(2);
		
		Link newHead2 = list.deleteHead(); //TestID: 3 - c1.b3, c2.b1
		// expected the new head, which is 1. Returns deleted head, which is 2
		assertEquals(2, newHead2.value);
		
		assertEquals(1, list.size());
		assertEquals(2, list.getHead().value);
		assertEquals(2, list.getTail().value);
	}

	// test 1 gives NullPointerException
	// test 2 gives NullPointerException
	// test 3 runs, but has assertion error
	@Test
	public void testDeleteTail() {
		Link newTail0 = list.deleteTail(); //TestID: 1 - c1.b1, c2.b2
		assertEquals(null, newTail0);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(1); // note: head and tail
		
		Link newTail1 = list.deleteTail(); //TestID: 2 - c1.b2, c2.b2
		assertEquals(null, newTail1);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(1);
		list.insertTail(2);
		
		Link newTail2 = list.deleteTail(); //TestID: 3 - c1.b3, c2.b1
		// expected the new tail, which is 1. Returns deleted tail, which is 2
		assertEquals(1, newTail2.value);
		
		assertEquals(1, list.size());
		assertEquals(1, list.getHead().value);
		assertEquals(1, list.getTail().value);
	}

	// test 1 gives NullPointerException
	// test 2 gives NullPointerException
	// test 3 passes
	@Test
	public void testDelete() {
		Link deleted0 = list.delete(10); //TestID: 1 - c1.b1, c2.b1, c3.b2
		assertEquals(null, deleted0);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(-18);
		
		Link deleted1 = list.delete(-18); //TestID: 2 - c1.b2, c2.b2, c3.b1
		assertEquals(-18, deleted1.value);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(0);
		list.insertTail(1);
		
		Link deleted2 = list.delete(0); //TestID: 3 - c1.b3, c2.b3, c3.b1
		assertEquals(0, deleted2.value);
		
		assertEquals(1, list.size());
		assertEquals(1, list.getHead().value);
		assertEquals(1, list.getTail().value);
	}

	@Test
	public void testIsEmpty() {
		boolean result0  = list.isEmpty(); //TestID: 1 - c1.b1
		assertEquals(true, result0);
		
		assertEquals(0, list.size());
		assertEquals(null, list.getHead());
		assertEquals(null, list.getTail());
		
		reset();
		
		list.insertHead(0);
		
		boolean result1 = list.isEmpty(); //TestID: 2 - c1.b2
		assertEquals(false, result1);
		
		assertEquals(1, list.size());
		assertEquals(0, list.getHead().value);
		assertEquals(0, list.getTail().value);
		
		reset();
		
		list.insertHead(0);
		list.insertTail(1);
		
		boolean result2 = list.isEmpty(); //TestID: 3 - c1.b3
		assertEquals(false, result1);
		
		assertEquals(2, list.size());
		assertEquals(0, list.getHead().value);
		assertEquals(1, list.getTail().value);
	}
}
