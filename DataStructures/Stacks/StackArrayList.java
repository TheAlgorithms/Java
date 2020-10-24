package DataStructures.Stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * This class implements a Stack using an ArrayList.
 * <p>
 * A stack is exactly what it sounds like. An element gets added to the top of
 * the stack and only the element on the top may be removed.
 * <p>
 * This is an ArrayList Implementation of a stack, where size is not
 * a problem we can extend the stack as much as we want.
 */
public class StackArrayList <T> {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        StackArrayList<Integer> stackInteger = new StackArrayList<>();
        assert stackInteger.isEmpty();

        for (int i = 1; i <= 5; ++i) {
            stackInteger.push(i);
            assert stackInteger.size() == i;
        }

        assert stackInteger.size() == 5;
        assert stackInteger.peek() == 5 && stackInteger.pop() == 5 && stackInteger.peek() == 4;

        /* pop elements at the top of this stack one by one */
        while (!stackInteger.isEmpty()) {
            stackInteger.pop();
        }
        assert stackInteger.isEmpty();

        try {
            stackInteger.pop();
            assert false; /* this should not happen */
        } catch (EmptyStackException e) {
            assert true; /* this should happen */
        }
        
        StackArrayList<String> stackString = new StackArrayList<>();
        assert stackString.isEmpty();

        String[] strings = {"Hello", "Hi", "Hey", "Hola", "Salut"};
        for (int i = 0; i < strings.length; ++i) {
            stackString.push(strings[i]);
            assert stackString.size() == i+1;
        }

        assert stackString.size() == 5;
        assert "Salut".equals(stackString.peek()) && "Salut".equals(stackString.pop()) && "Hola".equals(stackString.peek());

        /* pop elements at the top of this stack one by one */
        while (!stackString.isEmpty()) {
            stackString.pop();
        }
        assert stackString.isEmpty();

        try {
            stackString.pop();
            assert false; /* this should not happen */
        } catch (EmptyStackException e) {
            assert true; /* this should happen */
        }

    }

    /**
     * ArrayList representation of the stack
     */
    private ArrayList<T> stack;

    /**
     * Constructor
     */
    public StackArrayList() {
        stack = new ArrayList<>();
    }

    /**
     * Adds value to the end of list which
     * is the top for stack
     *
     * @param value value to be added
     */
    public void push(T value) {
        stack.add(value);
    }

    /**
     * Removes the element at the top of this stack and returns
     *
     * @return Element popped
     * @throws EmptyStackException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        /* remove the element on the top of the stack */
        return stack.remove(stack.size() - 1);
    }

    /**
     * Test if the stack is empty.
     *
     * @return {@code true} if this stack is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Return the element at the top of this stack without removing it from the stack.
     *
     * @return the element at the top of this stack.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * Return size of this stack.
     *
     * @return size of this stack.
     */
    public int size() {
        return stack.size();
    }
}