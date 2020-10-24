package DataStructures.Stacks;

/**
 * This class implements a Stack using a regular array.
 * <p>
 * A stack is exactly what it sounds like. An element gets added to the top of
 * the stack and only the element on the top may be removed. This is an example
 * of an array implementation of a Stack. So an element can only be added/removed
 * from the end of the array. In theory stack have no fixed size, but with an
 * array implementation it does.
 * @param <T>
 */
public class StackArray <T> {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        // Declare a stack of maximum size 4
        StackArray<Integer> myStackArrayInteger = new StackArray<>(4);

        assert myStackArrayInteger.isEmpty();
        assert !myStackArrayInteger.isFull();

        // Populate the stack
        myStackArrayInteger.push(5);
        myStackArrayInteger.push(8);
        myStackArrayInteger.push(2);
        myStackArrayInteger.push(9);

        assert !myStackArrayInteger.isEmpty();
        assert myStackArrayInteger.isFull();
        assert myStackArrayInteger.peek() == 9;
        assert myStackArrayInteger.pop() == 9;
        assert myStackArrayInteger.peek() == 2;
        assert myStackArrayInteger.size() == 3;
        
        
        StackArray<String> myStackArrayString = new StackArray<>(4);

        assert myStackArrayString.isEmpty();
        assert !myStackArrayString.isFull();

        // Populate the stack
        myStackArrayString.push("A");
        myStackArrayString.push("B");
        myStackArrayString.push("C");
        myStackArrayString.push("D");

        assert !myStackArrayString.isEmpty();
        assert myStackArrayString.isFull();
        assert "D".equals(myStackArrayString.peek());
        assert "D".equals(myStackArrayString.pop());
        assert "C".equals(myStackArrayString.peek());
        assert myStackArrayString.size() == 3;
    }

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The max size of the Stack
     */
    private int maxSize;

    /**
     * The array representation of the Stack
     */
    private T[] stackArray;

    /**
     * The top of the stack
     */
    private int top;

    /**
     * init Stack with DEFAULT_CAPACITY
     */
    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor
     *
     * @param size Size of the Stack
     */
    public StackArray(int size) {
        maxSize = size;
        stackArray = (T[]) new Object[maxSize];
        top = -1;
    }

    /**
     * Adds an element to the top of the stack
     *
     * @param value The element added
     */
    public void push(T value) {
        if (!isFull()) { // Checks for a full stack
            top++;
            stackArray[top] = value;
        } else {
            resize(maxSize * 2);
            push(value); // don't forget push after resizing
        }
    }

    /**
     * Removes the top element of the stack and returns the value you've removed
     *
     * @return value popped off the Stack
     */
    public T pop() {
        if (!isEmpty()) { // Checks for an empty stack
            return stackArray[top--];
        }

        if (top < maxSize / 4) {
            resize(maxSize / 2);
            return pop();// don't forget pop after resizing
        } else {
            System.out.println("The stack is already empty");
            return null;
        }
    }

    /**
     * Returns the element at the top of the stack
     *
     * @return element at the top of the stack
     */
    public T peek() {
        if (!isEmpty()) { // Checks for an empty stack
            return stackArray[top];
        } else {
            System.out.println("The stack is empty, cant peek");
            return null;
        }
    }

    private void resize(int newSize) {
        T[] transferArray = (T[]) new Object[newSize];

        System.arraycopy(stackArray, 0, transferArray, 0, stackArray.length);
        // This reference change might be nice in here
        stackArray = transferArray;
        maxSize = newSize;
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
    public void makeEmpty() { // Doesn't delete elements in the array but if you call
        top = -1;             // push method after calling makeEmpty it will overwrite previous values
    }

    /**
     * Return size of stack
     *
     * @return size of stack
     */
    public int size() {
        return top + 1;
    }
}