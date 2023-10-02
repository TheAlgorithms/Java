package com.thealgorithms.stacks;

import java.util.Arrays;
import java.util.Stack;

/*
Here is a algorithm for nextSmallerElement.
If there is no smaller number than current element it returns -1;
Given an array "input," you need to find and print the first smaller element for each element.
     Example:
     input = {2, 7, 3, 5, 4, 6, 8};
     At i = 0
     Next Smaller element between (i+1 to n) is -1
     At i = 1
     Next Smaller element between (i+1 to n) is 3
     At i = 2
     Next Smaller element between (i+1 to n) is -1
     At i = 3
     Next Smaller element between (i+1 to n) is 4
     At i = 4
     Next Smaller element between (i+1 to n) is -1
     At i = 5
     Next Smaller element between (i+1 to n) is -1
     At i = 6
     Next Smaller element between (i+1 to n) is -1
     
Result: [-1, 3, -1, 4, -1, -1, -1]
*/

public class NextSmallerElement {

    public static int[] findNextSmallerElements(int[] array) {
        if (array == null) {
            return array;
        }

        int[] result = new int[array.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                result[stack.pop()] = array[i];
            }
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 3, 5, 4, 6, 8};
        int[] result = findNextSmallerElements(input);
        System.out.println(Arrays.toString(result));
    }
}
