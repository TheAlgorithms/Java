package src.test.java.com.datastructure.stacks;

import org.junit.Test;

import src.main.java.com.datastructure.stacks.ArrayStack;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * this class runs tests for ArrayStack data structure
 *
 * @see ArrayStack
 */
public class ArrayStackTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor() {
        ArrayStack<String> arrayStack = new ArrayStack<>(-1);
    }

    @Test
    public void test_size_method() {
        ArrayStack<String> arrayStack;
        arrayStack = new ArrayStack<>(3);
        assertEquals(0, arrayStack.size());

        arrayStack.push("1");
        assertEquals(1, arrayStack.size());

        arrayStack.push("2");
        assertEquals(2, arrayStack.size());

        arrayStack.push("3");
        assertEquals(3, arrayStack.size());
    }

    @Test
    public void test_capacity_parameter() {
        ArrayStack<String> arrayStack;
        arrayStack = new ArrayStack<>(3);
        assertEquals(3, arrayStack.capacity);

        arrayStack = new ArrayStack<>(1);
        assertEquals(1, arrayStack.capacity);

        arrayStack = new ArrayStack<>(2);
        arrayStack.push("1");
        arrayStack.push("2");
        assertEquals(2, arrayStack.capacity);
    }

    @Test
    public void test_isEmpty_method() {
        ArrayStack<String> arrayStack;
        arrayStack = new ArrayStack<>(3);
        assertTrue(arrayStack.isEmpty());

        arrayStack.push("1");
        assertFalse(arrayStack.isEmpty());

        arrayStack.push("2");
        assertFalse(arrayStack.isEmpty());

        arrayStack.push("3");
        assertFalse(arrayStack.isEmpty());
    }

    @Test
    public void test_isFull_method() {
        ArrayStack<String> arrayStack = new ArrayStack<>(5);
        assertFalse(arrayStack.isFull());

        arrayStack.push("1");
        assertFalse(arrayStack.isFull());

        arrayStack.push("2");
        assertFalse(arrayStack.isFull());

        arrayStack.push("3");
        assertFalse(arrayStack.isFull());

        arrayStack.push("4");
        assertFalse(arrayStack.isFull());

        arrayStack.push("5");
        assertTrue(arrayStack.isFull());
    }

    @Test
    public void test_peek_method() {
        ArrayStack<String> arrayStack = new ArrayStack<>(1);

        // push a string
        arrayStack.push("1");

        // test peek
        assertEquals("1", arrayStack.peek());
        assertEquals("1", arrayStack.peek());
        assertEquals("1", arrayStack.peek());

        // pop the string
        arrayStack.pop();

        // test peek exception
        try {
            arrayStack.peek();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }

    @Test
    public void test_push_method() {
        // new instance
        ArrayStack<String> arrayStack = new ArrayStack<>(1);
        // test push
        arrayStack.push("1");
        // test push exception
        try {
            arrayStack.push("2");
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
        }
    }

    @Test
    public void test_pop_method() {
        // create instance
        ArrayStack<String> arrayStack = new ArrayStack<>();
        // push some Strings
        arrayStack.push("1");
        arrayStack.push("2");
        arrayStack.push("3");
        arrayStack.push("4");
        // test pop
        assertEquals("4", arrayStack.pop());
        assertEquals("3", arrayStack.pop());
        assertEquals("2", arrayStack.pop());
        assertEquals("1", arrayStack.pop());
        // test pop exception
        try {
            arrayStack.pop();
        } catch (Exception ex) {
            assertEquals(IndexOutOfBoundsException.class, ex.getClass());
        }
    }
}