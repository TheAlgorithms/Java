package datastructures.stacks;

import java.util.ArrayList;

/**
 * This is an ArrayList Implementation of stack, Where size is not
 * a problem we can extend the stack as much as we want.
 *
 * @author Unknown
 */
public class StackList {
    /**
     * ArrayList representation of the stack
     */
    private ArrayList<Integer> stackList;

    /**
     * Constructor
     */
    StackList() {
        stackList = new ArrayList<>();
    }

    /**
     * Adds value to the end of list which
     * is the top for stack
     *
     * @param value value to be added
     */
    public void push(int value) {
        stackList.add(value);
    }

    /**
     * Pops last element of list which is indeed
     * the top for Stack
     *
     * @return Element popped
     */
    public int pop() {
        if (!isEmpty()) {
            int popValue = stackList.get(stackList.size() - 1);
            stackList.remove(stackList.size() - 1);
            return popValue;
        } else {
            System.out.print("The stack is already empty  ");
            return -1;
        }
    }

    /**
     * Checks for empty Stack
     *
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Top element of stack
     *
     * @return top element of stack
     */
    public int peek() {
        return stackList.get(stackList.size() - 1);
    }
}
