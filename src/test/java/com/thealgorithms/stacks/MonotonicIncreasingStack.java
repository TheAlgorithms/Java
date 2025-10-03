/* Contributor: Nayan Saraff
 *
 * This Monotonic Increasing Stack is a popular algorithm which helps
 * in solving various problems including Stock Span, Trapping Rain Water
 */

import java.util.Arrays;
import java.util.Stack;

public final class MonotonicIncreasingStack {

    private MonotonicIncreasingStack() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            stack.push(i);
        }
        return result;
    }

    public static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
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

/* Reference: https://www.geeksforgeeks.org/dsa/introduction-to-monotonic-stack-2/ */
