package src.test.java.com.dataStructures;

import org.junit.Assert;
import org.junit.Test;
import src.main.java.com.dataStructures.Stack;

import java.util.EmptyStackException;

public class StackTest {

    @Test
    public void testEmpty() {

        Stack<Integer> myStack = new Stack<>();
        boolean isEmpty = myStack.empty();
        Assert.assertTrue(isEmpty);

        myStack.push(10);
        isEmpty = myStack.empty();
        Assert.assertFalse(isEmpty);
    }

    @Test(expected = EmptyStackException.class)
    public void testPeekWithoutElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.peek();
    }

    @Test
    public void testPeekWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);

        Assert.assertEquals(40, myStack.peek());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopWithoutElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.pop();

    }

    @Test
    public void testPopWithElements() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);

        Assert.assertEquals(50, myStack.pop());

    }

    @Test
    public void testPushWithinInitialCapacity() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);
        myStack.push(60);
        myStack.push(70);
        myStack.push(80);
        myStack.push(90);
        myStack.push(100);
        Assert.assertEquals(10, myStack.size());
    }

    @Test
    public void testPushOutsideInitialCapacity() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        myStack.push(40);
        myStack.push(50);
        myStack.push(60);
        myStack.push(70);
        myStack.push(80);
        myStack.push(90);
        myStack.push(100);
        myStack.push(110);
        Assert.assertEquals(11, myStack.size());
    }

    @Test
    public void testSearchWithObjectUnavailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        Assert.assertEquals(-1,myStack.search(50));
    }

    @Test
    public void testSearchWithObjectAvailable() {

        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(20);
        myStack.push(30);
        Assert.assertEquals(3,myStack.search(10));

    }
}
