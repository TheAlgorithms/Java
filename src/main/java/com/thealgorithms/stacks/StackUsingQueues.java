import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackUsingQueuesTest {
    private StackUsingQueues.StackQueues stack;

    @Before
    public void setUp() {
        stack = new StackUsingQueues.StackQueues();
    }

    @Test
    public void testPushAndTop() {
        stack.push(5);
        stack.push(2);
        stack.push(9);

        assertEquals(9, stack.top());
    }

    @Test
    public void testPushAndPop() {
        stack.push(5);
        stack.push(2);
        stack.push(9);

        assertEquals(9, stack.pop());
        assertEquals(2, stack.pop());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(5);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void testPopOnEmptyStack() {
        stack.pop();
    }

    @Test
    public void testTopOnEmptyStack() {
        stack.top();
    }
}
