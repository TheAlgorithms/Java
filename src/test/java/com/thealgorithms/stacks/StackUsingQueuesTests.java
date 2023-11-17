import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackUsingQueuesTest {

    private StackUsingQueues stack;

    @Before
    public void setUp() {
        stack = new StackUsingQueues();
    }

    @Test
    public void testPushAndTop() {
        stack.push(1);
        stack.push(2);
        assertEquals("Top element should be 2 after pushing 1 and 2", 2, stack.top());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        assertEquals("Popped element should be 2", 2, stack.pop());
        assertEquals("Top element should now be 1", 1, stack.top());
    }

    @Test
    public void testEmpty() {
        assertTrue("Stack should be empty initially", stack.empty());
        stack.push(1);
        assertFalse("Stack should not be empty after pushing an element", stack.empty());
        stack.pop();
        assertTrue("Stack should be empty after popping the only element", stack.empty());
    }

    @Test(expected = IllegalStateException.class)
    public void testPopEmptyStack() {
        stack.pop();
    }

    @Test(expected = IllegalStateException.class)
    public void testTopEmptyStack() {
        stack.top();
    }

    @Test
    public void testPushMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);

        assertEquals("Top element should be 7 after pushing 1 to 7", 7, stack.top());
    }
}
