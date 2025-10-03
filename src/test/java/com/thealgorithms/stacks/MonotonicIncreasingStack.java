/*Contributor: Nayan Saraff

This Monotonic Increasing Stack is a popular algorithm which helps
in solving various problems including the Stock Span, Trapping Rain water*/


import java.util.*;

public class MonotonicStack {

    // Returns Next Greater Element for each element in the array
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            // Pop elements smaller or equal to arr[i]
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }

            // If stack is empty, no greater element to the right
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];

            // Push current index onto stack
            stack.push(i);
        }

        return result;
    }

    // Returns Next Smaller Element for each element in the array
    public static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            // Pop elements greater or equal to arr[i]
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};

        int[] nextGreater = nextGreaterElement(arr);
        int[] nextSmaller = nextSmallerElement(arr);

        System.out.println("Next Greater Element: " + Arrays.toString(nextGreater));
        System.out.println("Next Smaller Element: " + Arrays.toString(nextSmaller));
    }
}
