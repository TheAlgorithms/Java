package datastructures.stacks;

/**
 * This class implements a Stack using two different implementations.
 * Stack is used with a regular array and Stack2 uses an ArrayList.
 * <p>
 * A stack is exactly what it sounds like. An element gets added to the top of
 * the stack and only the element on the top may be removed. This is an example
 * of an array implementation of a Stack. So an element can only be added/removed
 * from the end of the array. In theory stack have no fixed size, but with an
 * array implementation it does.
 *
 * @author Unknown
 */
public class StackArray {
    /**
     * The max size of the Stack
     */
    private int maxSize;
    /**
     * The array representation of the Stack
     */
    private int[] stackArray;
    /**
     * The top of the stack
     */
    private int top;

    /**
     * Constructor
     *
     * @param size Size of the Stack
     */
    public StackArray(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    /**
     * Adds an element to the top of the stack
     *
     * @param value The element added
     */
    public void push(int value) {
        if (!isFull()) {
            top++;
            stackArray[top] = value;
        } else {
            System.out.println("The stack is full, can't insert value");
        }
    }

    /**
     * Removes the top element of the stack and returns the value you've removed
     *
     * @return value popped off the Stack
     */
    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            System.out.println("The stack is already empty");
            return -1;
        }
    }

    /**
     * Returns the element at the top of the stack
     *
     * @return element at the top of the stack
     */
    public int peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            System.out.println("The stack is empty, cant peek");
            return -1;
        }
    }

    /**
     * Returns true if the stack is empty
     *
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Returns true if the stack is full
     *
     * @return true if the stack is full
     */
    public boolean isFull() {
        return (top + 1 == maxSize);
    }

    /**
     * Deletes everything in the Stack
     * <p>
     * Doesn't delete elements in the array
     * but if you call push method after calling
     * makeEmpty it will overwrite previous
     * values
     */
    public void makeEmpty() {
        top = -1;
    }
}