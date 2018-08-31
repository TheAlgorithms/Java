package src.main.java.com.datastructure.stacks;

/**
 * ArrayStack is a linear data structure that is an implementation for the stack with array,
 * and it considered the easiest way to implement stack with one array inside.
 *
 * @param <E> the type of element
 * @see Stack
 */
public class ArrayStack<E> implements Stack<E> {
    /**
     * Is the default capacity of the ArrayStack when empty constructor is used.
     * Q) Why 10 ?
     * A) There is no particular answer for this question and you can change the default capacity
     * if you want, It's just an unsystematic number.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Capacity is the max number of elements can ArrayStack hold.
     */
    public final int capacity;
    /**
     * Array to store the ArrayStack elements.
     */
    private final E[] data;
    /**
     * The index of the top element which is the last element stored in the ArrayStack,
     * can be -1 if there is the ArrayStack is empty.
     */
    private int top;

    /**
     * Used to create a ArrayStack data type with a capacity of the integer capacity parameter.
     *
     * @param capacity the max size that can ArrayStack hold.
     * @throws IllegalArgumentException if the capacity entered is negative number
     * @see ArrayStack
     */
    @SuppressWarnings("unchecked")
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
     *
     * @see ArrayStack
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

    /**
     * Used to figure if the ArrayStack contains elements or not,
     * ArrayStack is empty if and only if there is no elements inside.
     *
     * @return true if the ArrayStack is empty or false if not
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the number of elements inside the ArrayStack now
     *
     * @return number of elements
     */
    public int size() {
        return top + 1;
    }

    /**
     * Used to figure if the ArrayStack is full or not,
     * ArrayStack is full means that we can't add any more elements.
     *
     * @return true if the ArrayStack is full or false if not
     */
    public boolean isFull() {
        return this.size() == capacity;
    }

    /**
     * Used to get the array of data from the ArrayStack,
     * notice that this method returns a reference for same array it uses
     *
     * @return the saved array of E
     */
    public E[] toArray() {
        return data;
    }
}
