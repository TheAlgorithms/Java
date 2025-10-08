/**
 * Contributor: Nayan Saraff
 *
 * A Monotonic Increasing Stack is an algorithmic pattern used to solve
 * problems such as Stock Span, Trapping Rain Water, and Next Greater Element.
 * It maintains a stack where elements are in increasing order to efficiently
 * find relationships between elements based on their relative values.
 *
 * Reference:
 * https://www.geeksforgeeks.org/dsa/introduction-to-monotonic-stack-2/
 */

import java.util.Stack;

public final class MonotonicIncreasingStack {

    private MonotonicIncreasingStack() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Finds the next greater element for each element in the given array.
     * 
     * For each element, it returns the nearest greater element to its right.
     * If no such element exists, -1 is returned for that index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
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

    /**
     * Finds the next smaller element for each element in the given array.
     *
     * For each element, it returns the nearest smaller element to its right.
     * If no such element exists, -1 is returned for that index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
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

    // Test class included in the same file
    public static void main(String[] args) {
        testScenario(new int[]{2, 5, 1, 3, 4});
        testScenario(new int[]{1, 2, 3, 4, 5});
        testScenario(new int[]{5, 4, 3, 2, 1});
    }

    private static void testScenario(int[] arr) {
        int[] nextGreater = nextGreaterElement(arr);
        int[] nextSmaller = nextSmallerElement(arr);

        System.out.print("Array: ");
        printArray(arr);
        System.out.print("Next Greater: ");
        printArray(nextGreater);
        System.out.print("Next Smaller: ");
        printArray(nextSmaller);
        System.out.println("-----------------------------");
    }

    private static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }
}
