package com.thealgorithms.datastructures.stacks;

import java.util.Arrays;
import java.util.Stack;

/*
    Given an array "input" you need to print the first smaller element for each element to the left side of an array.
    For a given element x of an array, the Next Smaller element of that element is the 
    first smaller element to the left side of it. If no such element is present print -1.
    
    Example
    input = { 2, 7, 3, 5, 4, 6, 8 };
    At i = 0
    No elements to left of it : -1
    At i = 1
    Next smaller element between (0 , 0) is 2
    At i = 2
    Next smaller element between (0 , 1) is 2
    At i = 3
    Next smaller element between (0 , 2) is 3
    At i = 4
    Next smaller element between (0 , 3) is 3
    At i = 5
    Next smaller element between (0 , 4) is 4
    At i = 6
    Next smaller element between (0 , 5) is 6
    
    result : [-1, 2, 2, 3, 3, 4, 6]
    
    1) Create a new empty stack st
    
    2) Iterate over array "input" , where "i"  goes from 0 to input.length -1.
        a) We are looking for value just smaller than `input[i]`. So keep popping from "stack" 
           till elements in "stack.peek() >= input[i]" or stack becomes empty.
        b) If the stack is non-empty, then the top element is our previous element. Else the previous element does not exist. 
        c) push input[i] in stack.
    3) If elements are left then their answer is -1
 */

public class NextSmallerElement {

    public static int[] findNextSmallerElements(int[] array) {
        // base case
        if (array == null) {
            return array;
        }
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[array.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < array.length; i++) {
            while (!stack.empty() && stack.peek() >= array[i]) stack.pop();
            if (stack.empty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(array[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = { 2, 7, 3, 5, 4, 6, 8 };
        int[] result = findNextSmallerElements(input);
        System.out.println(Arrays.toString(result));
    }
}
