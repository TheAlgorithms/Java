package src.main.java.com.datastructure.stacks;

/**
 * ArrayStack is a linear data structure that is an implementation for the stack with array,
 * and it considered the easiest way to implement stack with one array inside.
 *
 * @param <E> the type of element
 * @see Stack
 */
public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 10;

    public final int capacity;


    private final E[] data;


    private int top;


    public ArrayStack(int capacity) throws IllegalArgumentException {
        if (capacity < 0) {
            throw new IllegalArgumentException("ArrayStack capacity can't be negative");
        }
        this.capacity = capacity;
        data = (E[]) new Object[capacity];
        top = -1;
    }

    /**
     * Used to create a ArrayStack data type with the default capacity (10).
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Removes and returns the most recently added element from the ArrayStack.
     *
     * @return element removed (or null if empty)
     */
    public E pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("ArrayStack is empty so you " +
                    "cannot request last element entered using the pop method");
        return data[top--];
    }

    /**
     * Adds the element entered to the top of the ArrayStack
     *
     * @param element to add
     * @throws IllegalArgumentException if the ArrayStack is full
     */
    public void push(E element) throws IllegalArgumentException {
        if (isFull())
            throw new IllegalArgumentException("ArrayStack is full, can't push" +
                    element + "to the ArrayStack");
        data[++top] = element;
    }

    /**
     * Returns the most recently added element to the ArrayStack without modify any thing
     *
     * @return top element
     */
    public E peek() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("ArrayStack is empty so you " +
                    "cannot request last element entered using the peek method");
        return data[top];
    }


    public boolean isEmpty() {
        return top == -1;
    }


    public int size() {
        return top + 1;
    }


    public boolean isFull() {
        return this.size() == capacity;
    }


    public E[] toArray() {
        return data;
    }
}
