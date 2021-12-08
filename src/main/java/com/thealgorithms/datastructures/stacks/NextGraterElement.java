package com.thealgorithms.datastructures.stacks;
import java.util.Arrays;
import java.util.Stack;
/*
    Given an array "input" you need to print the first grater element for each element.
    For a given element x of an array, the Next Grater element of that element is the
    first grater element to the right side of it. If no such element is present print -1.

    Example
    input = { 2, 7, 3, 5, 4, 6, 8 };
    At i = 0
    Next Grater element between (1 to n) is 7
    At i = 1
    Next Grater element between (2 to n) is 8
    At i = 2
    Next Grater element between (3 to n) is 5
    At i = 3
    Next Grater element between (4 to n) is 6
    At i = 4
    Next Grater element between (5 to n) is 6
    At i = 5
    Next Grater element between (6 to n) is 8
    At i = 6
    Next Grater element between (6 to n) is -1

    result : [7, 8, 5, 6, 6, 8, -1]

    1. If the stack is empty Push an element in the stack.
    2. If the stack is not empty:
        a.  compare the top element of the stack with next.
        b.  If next is greater than the top element, Pop element from the stack.
            next is the next greater element for the popped element.
        c.  Keep popping from the stack while the popped element is smaller
            than next. next becomes the next greater element for all such
            popped elements.
        d. Finally, push the next in the stack.

    3. If elements are left in stack after completing while loop then their Next Grater element is -1.
 */


public class NextGraterElement {
 
    public static int[] findNextGreaterElements(int[] array) {

        if (array == null) {
            return array;
        }

        int[] result = new int[array.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] < array[i]) {
                result[stack.pop()] = array[i];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args)
    {
        int[] input = { 2, 7, 3, 5, 4, 6, 8 };
        int[] result = findNextGreaterElements(input);
        System.out.println(Arrays.toString(result));
    }
}
