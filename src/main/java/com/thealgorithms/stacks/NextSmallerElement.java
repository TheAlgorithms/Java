package com.thealgorithms.stacks;

import java.util.Arrays;
import java.util.Stack;

/*Given an array "input" you need to print the first smaller element for each element to the right
   side of an array. For a given element x of an array, the next Smaller element of that element is
   the first smaller element to the right side of it. If no such element is present print -1.

    Example
    input = {4, 8, 5, 2, 25};
    At i = 0
    Next smaller element to its right is 2
    At i = 1
    Next smaller element to its right is 5
    At i = 2
    Next smaller element to its right is 2
    At i = 3
    No smaller element at right so -1;
    At i = 4
    No smaller element at right so -1;

    result : [2, 5, 2, -1, -1]

    1) Create a new empty stack st

    2) Iterate over array "input" , where "i"  goes from input.length-1 to 1.
        a) We are looking for value just smaller than `input[i]`. So keep popping from "stack"
           till elements in "input[stack.peek()] >= input[i]" or stack becomes empty.
        b) If the stack is non-empty, then the top element is our previous element. Else the
   previous element does not exist. c) push i in stack. 3) If stack is empty then their
   answer is -1
 */

public class NextSmallerElement {
     public static int[] nextS(int[] input) {
        Stack<Integer> S=new Stack<>();
        int [] nextSmaller=new int[input.length];
        for (int i=input.length-1;i>=0;i--){
            while (!S.isEmpty() && input[S.peek()]>=input[i]){
                S.pop();
            }
            if(S.isEmpty()){
                nextSmaller[i]=-1;
            }
            else {
                nextSmaller[i]=input[S.peek()];
            }
            S.add(i);
        }
        return nextSmaller;
    }

    public static void main(String[] args) {
        int[] input = {4,8,5,2,25};
        int[] result = nextS(input);
        System.out.println(Arrays.toString(result));
    }
}
