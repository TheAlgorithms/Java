package datastructures.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * The {@code NearestElement} class provides static utility methods to find the nearest greater or smaller elements
 * to the left or right of each element in an integer array using stack-based algorithms.
 * 
 * <p>Each method runs in O(n) time complexity by maintaining a monotonic stack:
 * <ul>
 *   <li>{@code nearestGreaterToRight}: Finds the nearest greater element to the right of each element.</li>
 *   <li>{@code nearestGreaterToLeft}: Finds the nearest greater element to the left of each element.</li>
 *   <li>{@code nearestSmallerToRight}: Finds the nearest smaller element to the right of each element.</li>
 *   <li>{@code nearestSmallerToLeft}: Finds the nearest smaller element to the left of each element.</li>
 * </ul>
 * If no such element exists for a position, -1 is returned at that index.
 */
public class NearestElement {
    public static int[] nearestGreaterToRight(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    public static int[] nearestGreaterToLeft(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek() <= arr[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    public static int[] nearestSmallerToRight(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    public static int[] nearestSmallerToLeft(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek() >= arr[i]) st.pop();
            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        System.out.println("Nearest Greater to Right: " + Arrays.toString(nearestGreaterToRight(arr)));
        System.out.println("Nearest Greater to Left: " + Arrays.toString(nearestGreaterToLeft(arr)));
        System.out.println("Nearest Smaller to Right: " + Arrays.toString(nearestSmallerToRight(arr)));
        System.out.println("Nearest Smaller to Left: " + Arrays.toString(nearestSmallerToLeft(arr)));
    }
}
