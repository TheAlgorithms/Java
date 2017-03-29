package data_structures.stacks;

import java.util.ArrayList;

/*
This is ArrayList Implementation of stack , Where size is not
a problem we can extend the stack as much as we want
*/
public class Stack2 {
    ArrayList<Integer> stackList;

    Stack2() { //constructor
        stackList = new ArrayList<>();
    }


    void push(int value) { //adds value to the end of list which is the top for stack
        stackList.add(value);
    }

    int pop() { //pops last element of list which is indeed the top for Stack

        if (!isEmpty()) { // checks for an empty Stack

            int popValue = stackList.get(stackList.size() - 1);
            stackList.remove(stackList.size() - 1);  //removes the poped element from the list
            return popValue;
        } else {
            System.out.print("The stack is already empty  ");
            return -1;
        }

    }

    boolean isEmpty() { //checks for empty Stack
        return stackList.isEmpty();

    }

    int peek() { //top element of stack
        return stackList.get(stackList.size() - 1);
    }
}
